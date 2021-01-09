package nearby.poi.soap.endpoints;

import nearby.poi.soap.dto.PointOfInterestDTO;
import nearby.poi.soap.interfaces.PointOfInterestWebServiceInterface;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "nearby.poi.soap.interfaces.PointOfInterestWebServiceInterface")
public class PointOfInterestWebService implements PointOfInterestWebServiceInterface {

    @Override
    @WebMethod
    public PointOfInterestDTO getClosestPointOfInterest(float latitude, float longitude) {
        PointOfInterestDTO pointOfInterestDTO = new PointOfInterestDTO();
        System.out.println(latitude);
        System.out.println(longitude);
        pointOfInterestDTO.setName("fff");
        return pointOfInterestDTO;
    }
}
