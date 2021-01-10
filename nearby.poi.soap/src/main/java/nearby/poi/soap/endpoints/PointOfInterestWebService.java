package nearby.poi.soap.endpoints;

import nearby.poi.domain.LatLongDTO;
import nearby.poi.domain.PointOfInterest;
import nearby.poi.exceptions.PointOfInterestNotFoundException;
import nearby.poi.service.PointOfInterestService;
import nearby.poi.soap.cache.PointOfInterestCache;
import nearby.poi.soap.dto.PointOfInterestDTO;
import nearby.poi.soap.interfaces.PointOfInterestWebServiceInterface;
import nearby.poi.soap.utils.PointOfInterestMapper;
import org.slf4j.Logger;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@WebService(endpointInterface = "nearby.poi.soap.interfaces.PointOfInterestWebServiceInterface")
public class PointOfInterestWebService implements PointOfInterestWebServiceInterface {

    @Inject
    private PointOfInterestCache pointOfInterestCache;

    @Inject
    private PointOfInterestService pointOfInterestService;

    @Inject
    private Logger logger;

    @Override
    @WebMethod
    public PointOfInterestDTO getClosestPointOfInterest(float latitude, float longitude) {
        logger.info("Called with {} lat and {} long",latitude,longitude);
        if(latitude == 0 || longitude==0) {
            logger.error("Lat Long cannot be zero");
            throw new RuntimeException("Lat Long cannot be zero");
        }
        PointOfInterest closestPointOfInterest = null;
        LatLongDTO requestedPosition = new LatLongDTO(BigDecimal.valueOf(latitude), BigDecimal.valueOf(longitude));
        try {
            closestPointOfInterest = pointOfInterestService.findClosestPointOfInterest(pointOfInterestCache.getPointOfInterestList(), requestedPosition);
        } catch (PointOfInterestNotFoundException e) {
            logger.error("Found no point of interest");
            return new PointOfInterestDTO();
        }
        pointOfInterestCache.increaseCounterOfNearbyPointOfInterest(closestPointOfInterest);
        return PointOfInterestMapper.convert(closestPointOfInterest);
    }

    @Override
    public List<PointOfInterestDTO> getPointOfInterestsGreaterThanCount(int count) {
        logger.info("Called with count {}",count);
        if(count == 0) {
            return pointOfInterestCache.getPointOfInterestList()
                    .stream().map(PointOfInterestMapper::convert).collect(Collectors.toList());
        }else{
            return pointOfInterestCache.getCountOfNearbyPointOfInterests()
                    .entrySet()
                    .stream()
                    .filter(map -> map.getValue() >= count)
                    .map(Map.Entry::getKey)
                    .map(PointOfInterestMapper::convert)
                    .collect(Collectors.toList());
        }
    }

    private PointOfInterest findClosestPointOfInterest(List<PointOfInterest> pointOfInterests,LatLongDTO position){
        try {
            return pointOfInterestService.findClosestPointOfInterest(pointOfInterestCache.getPointOfInterestList(),position);
        } catch (PointOfInterestNotFoundException e) {
            throw new RuntimeException("Point not found");
        }
    }

}
