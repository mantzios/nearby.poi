package nearby.poi.soap.utils;

import nearby.poi.interfaces.PointOfInterestRepository;
import nearby.poi.service.PointOfInterestService;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class PointOfInterestServiceProducer {


    @EJB
    private PointOfInterestRepository pointOfInterestRepository;

    @Produces
    public PointOfInterestService getPointOfInterestService(){
        return new PointOfInterestService(pointOfInterestRepository);
    }
}
