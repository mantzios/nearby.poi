package nearby.poi.soap.client.main;

import nearby.poi.soap.endpoints.PointOfInterestWebServiceInterface;
import nearby.poi.soap.endpoints.PointOfInterestWebServiceService;
import nearby.poi.soap.interfaces.PointOfInterestDTO;

import java.net.MalformedURLException;
import java.net.URL;

public class ClosestPointOfInterestMain {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/nearby.poi.soap-1.0-SNAPSHOT/PointOfInterestWebService?wsdl");

        PointOfInterestWebServiceService employeeService_Service
                = new PointOfInterestWebServiceService(url);
        PointOfInterestWebServiceInterface employeeServiceProxy = employeeService_Service.getPointOfInterestWebServicePort();

        PointOfInterestDTO closestPointOfInterest = employeeServiceProxy.getClosestPointOfInterest(31.2f, 31.2f);
        System.out.println(closestPointOfInterest.getName());
    }

}

