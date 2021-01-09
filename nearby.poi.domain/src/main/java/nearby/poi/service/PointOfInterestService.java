package nearby.poi.service;

import nearby.poi.domain.LatLongDTO;
import nearby.poi.domain.POIDistance;
import nearby.poi.domain.PointOfInterest;
import nearby.poi.interfaces.DistanceMetricInterface;
import nearby.poi.interfaces.PointOfInterestRepository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class PointOfInterestService implements Serializable {

    private PointOfInterestRepository pointOfInterestRepository;

    public PointOfInterestService(PointOfInterestRepository pointOfInterestRepository) {
        this.pointOfInterestRepository = pointOfInterestRepository;
    }

    public PointOfInterestService() {
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
    public PointOfInterest findClosestPointOfInterest(List<PointOfInterest> pointOfInterests, LatLongDTO currentPosition, DistanceMetricInterface distanceMetricInterface) {
        return getClosestPointOfInterest(pointOfInterests, currentPosition, distanceMetricInterface);
    }

    /**
     * Returns the closest point of interest based on current position
     * By default it uses the haversine metric
     * @param pointOfInterests the list of point of interests
     * @param currentPosition the current position
     * @return the closest point of interest
     */
    public PointOfInterest findClosestPointOfInterest(List<PointOfInterest> pointOfInterests, LatLongDTO currentPosition) {
        return getClosestPointOfInterest(pointOfInterests, currentPosition, new HavershineMetric());
    }

    private PointOfInterest getClosestPointOfInterest(List<PointOfInterest> pointOfInterests, LatLongDTO currentPosition, DistanceMetricInterface distanceMetricInterface) {
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

}
