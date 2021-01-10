package nearby.poi.service;

import nearby.poi.domain.LatLongDTO;
import nearby.poi.domain.POIDistance;
import nearby.poi.domain.PointOfInterest;
import nearby.poi.exceptions.PointOfInterestNotFoundException;
import nearby.poi.exceptions.ValidationException;
import nearby.poi.interfaces.DistanceMetricInterface;
import nearby.poi.interfaces.PointOfInterestRepository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PointOfInterestService implements Serializable {

    private final PointOfInterestRepository pointOfInterestRepository;
    private final ExecutorService executorService;
    private Integer numberOfChunks;

    public PointOfInterestService(PointOfInterestRepository pointOfInterestRepository, ExecutorService executorService, Integer numberOfChunks) {
        this.pointOfInterestRepository = pointOfInterestRepository;
        this.executorService = executorService;
        this.numberOfChunks = numberOfChunks;
    }

    public List<PointOfInterest> getAllPointOfInterest(){
        return pointOfInterestRepository.findAllPointsOfInterest();
    }

    /**
     * Returns the closest point of interest based on current position
     *
     * @param pointOfInterests    the list of point of interests
     * @param currentPosition     the current position
     * @param distanceMetricInterface a way to specify how to measure the distance between two points
     * @return the closest point of interest
     */
    public PointOfInterest findClosestPointOfInterest(List<PointOfInterest> pointOfInterests, LatLongDTO currentPosition,
                                                      DistanceMetricInterface distanceMetricInterface) throws PointOfInterestNotFoundException {
        validateLatLong(currentPosition);
        return getClosestPointOfInterestAsynchronous(pointOfInterests, currentPosition, distanceMetricInterface);
    }

    /**
     * Returns the closest point of interest based on current position
     * By default it uses the haversine metric
     * @param pointOfInterests the list of point of interests
     * @param currentPosition the current position
     * @return the closest point of interest
     */
    public PointOfInterest findClosestPointOfInterest(List<PointOfInterest> pointOfInterests, LatLongDTO currentPosition)
            throws PointOfInterestNotFoundException {
        validateLatLong(currentPosition);
        return getClosestPointOfInterestAsynchronous(pointOfInterests, currentPosition, new HavershineMetric());
    }

    private void validateLatLong(LatLongDTO currentPosition) {
        if(currentPosition == null) throw new ValidationException("Current Position cannot be null");
        if(currentPosition.getLatitude() == null) throw new ValidationException("Latitude cannot be null");
        if(currentPosition.getLongitude() == null) throw new  ValidationException("Longitude cannot be null");
    }

    private PointOfInterest getClosestPointOfInterest(List<PointOfInterest> pointOfInterests, LatLongDTO currentPosition,
                                                      DistanceMetricInterface distanceMetricInterface) throws ValidationException {
        if(pointOfInterests == null) throw new ValidationException("point of interests cannot be null");
        if(currentPosition == null) throw new ValidationException("current position cannot be null");
        return pointOfInterests
                .stream()
                .map((pointOfInterest) -> {
                    BigDecimal distanceFromCurrentPosition = distanceMetricInterface.distance(pointOfInterest.getPosition(), currentPosition);
                    return new POIDistance(pointOfInterest, distanceFromCurrentPosition);
                })
                .min(Comparator.comparing(POIDistance::getDistanceFromCurrentPosition))
                .orElseThrow(RuntimeException::new)
                .getPointOfInterest();
    }

    private PointOfInterest getClosestPointOfInterestAsynchronous(List<PointOfInterest> pointOfInterests, LatLongDTO currentPosition,
                                                                  DistanceMetricInterface distanceMetricInterface) throws PointOfInterestNotFoundException {
        if(pointOfInterests == null || pointOfInterests.size() == 0) throw new PointOfInterestNotFoundException("Points of interests are empty");
        int sizeOfWholeList = pointOfInterests.size();
        List<CompletableFuture<PointOfInterest>> listOfCompletableFutures = new ArrayList<>();
        int chunk = sizeOfWholeList / numberOfChunks;
        runAsyncComputationOfClosestPointOfInterest(pointOfInterests, currentPosition, distanceMetricInterface, sizeOfWholeList, listOfCompletableFutures, chunk);
        CompletableFuture<Void> joinedOfCompletableFuture = CompletableFuture.allOf(listOfCompletableFutures.toArray(new CompletableFuture[listOfCompletableFutures.size()]));
        joinedOfCompletableFuture.join(); // blocks until all the computation has been completed
        List<PointOfInterest> closestPointOfInterests = new ArrayList<>();
        listOfCompletableFutures.forEach(coml -> coml.thenAccept(closestPointOfInterests::add));
        return getClosestPointOfInterest(closestPointOfInterests, currentPosition,distanceMetricInterface);
    }

    private void runAsyncComputationOfClosestPointOfInterest(List<PointOfInterest> pointOfInterests, LatLongDTO currentPosition, DistanceMetricInterface distanceMetricInterface, int sizeOfWholeList, List<CompletableFuture<PointOfInterest>> listOfCompletableFutures, int chunk) {
        for(int i=0; i< numberOfChunks; i++){
            int startInclusive = chunk * i;
            int endExclusive = i == 2 ? sizeOfWholeList : chunk * (i+1);
            List<PointOfInterest> chunkedList = IntStream.range(startInclusive, endExclusive)
                    .mapToObj(pointOfInterests::get)
                    .collect(Collectors.toList());
            listOfCompletableFutures.add(CompletableFuture.supplyAsync(
                    ()-> getClosestPointOfInterest(chunkedList,currentPosition,distanceMetricInterface),
                    executorService));
        }
    }

    public Integer getNumberOfChunks() {
        return numberOfChunks;
    }

    public void setNumberOfChunks(Integer numberOfChunks) {
        this.numberOfChunks = numberOfChunks;
    }
}
