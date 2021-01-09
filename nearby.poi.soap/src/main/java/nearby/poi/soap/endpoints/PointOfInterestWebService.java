package nearby.poi.soap.endpoints;

import nearby.poi.domain.LatLongDTO;
import nearby.poi.domain.PointOfInterest;
import nearby.poi.service.PointOfInterestService;
import nearby.poi.soap.cache.PointOfInterestCache;
import nearby.poi.soap.dto.PointOfInterestDTO;
import nearby.poi.soap.interfaces.PointOfInterestWebServiceInterface;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.math.BigDecimal;

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
        PointOfInterestDTO pointOfInterestDTO = new PointOfInterestDTO();
        pointOfInterestDTO.setName(closestPointOfInterest.getName());
        return pointOfInterestDTO;
    }
}
