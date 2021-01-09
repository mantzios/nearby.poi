package nearby.poi.soap.client.main;

import nearby.poi.soap.endpoints.PointOfInterestWebServiceInterface;
import nearby.poi.soap.endpoints.PointOfInterestWebServiceService;
import nearby.poi.soap.interfaces.PointOfInterestDTO;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ClosestPointOfInterestMain {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/nearby.poi.api/PointOfInterestWebService?wsdl");

        PointOfInterestWebServiceService employeeService_Service
                = new PointOfInterestWebServiceService(url);
        PointOfInterestWebServiceInterface employeeServiceProxy = employeeService_Service.getPointOfInterestWebServicePort();

        PointOfInterestDTO closestPointOfInterest = employeeServiceProxy.getClosestPointOfInterest(37.955654f , 23.698765f);
        System.out.println(closestPointOfInterest.getName());

        List<PointOfInterestDTO> pointOfInterestsGreaterThanCount = employeeServiceProxy.getPointOfInterestsGreaterThanCount(2);
        pointOfInterestsGreaterThanCount.forEach(point-> System.out.println(point.getName()));
    }

}

