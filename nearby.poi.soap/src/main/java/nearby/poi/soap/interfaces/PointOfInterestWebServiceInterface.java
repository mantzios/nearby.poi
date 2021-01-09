package nearby.poi.soap.interfaces;

import nearby.poi.soap.dto.PointOfInterestDTO;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface PointOfInterestWebServiceInterface {


    @WebMethod
    PointOfInterestDTO getClosestPointOfInterest(float latitude,float longitude);
}
