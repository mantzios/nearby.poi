package nearby.poi.soap.interfaces;

import nearby.poi.soap.dto.PointOfInterestDTO;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface PointOfInterestWebServiceInterface {


    @WebMethod
    PointOfInterestDTO getClosestPointOfInterest(float latitude,float longitude);

    @WebMethod
    List<PointOfInterestDTO> getPointOfInterestsGreaterThanCount(int count);
}
