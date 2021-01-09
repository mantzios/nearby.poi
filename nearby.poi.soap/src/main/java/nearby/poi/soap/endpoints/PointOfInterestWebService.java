package nearby.poi.soap.endpoints;

import nearby.poi.domain.LatLongDTO;
import nearby.poi.domain.PointOfInterest;
import nearby.poi.service.PointOfInterestService;
import nearby.poi.soap.cache.PointOfInterestCache;
import nearby.poi.soap.dto.PointOfInterestDTO;
import nearby.poi.soap.interfaces.PointOfInterestWebServiceInterface;
import nearby.poi.soap.utils.PointOfInterestMapper;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebService(endpointInterface = "nearby.poi.soap.interfaces.PointOfInterestWebServiceInterface")
public class PointOfInterestWebService implements PointOfInterestWebServiceInterface {

    @Inject
    private PointOfInterestCache pointOfInterestCache;

    @Inject
    private PointOfInterestService pointOfInterestService;

    @Override
    @WebMethod
    public PointOfInterestDTO getClosestPointOfInterest(float latitude, float longitude) {
        PointOfInterest closestPointOfInterest = pointOfInterestService.findClosestPointOfInterest(pointOfInterestCache.getPointOfInterestList(),
                new LatLongDTO(BigDecimal.valueOf(latitude), BigDecimal.valueOf(longitude)));
        pointOfInterestCache.increaseCounterOfNearbyPointOfInterest(closestPointOfInterest);
        return PointOfInterestMapper.convert(closestPointOfInterest);
    }

    @Override
    public List<PointOfInterestDTO> getPointOfInterestsGreaterThanCount(int count) {
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
}
