package nearby.poi.soap.utils;

import nearby.poi.interfaces.PointOfInterestRepository;
import nearby.poi.service.PointOfInterestService;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class PointOfInterestServiceProducer {


    private static final Integer MAXIMUM_NUMBER_OF_CHUNKS = 3;

    @EJB
    private PointOfInterestRepository pointOfInterestRepository;

    @Resource
    private ManagedExecutorService managedExecutorService;

    @Produces
    public PointOfInterestService getPointOfInterestService(){
        return new PointOfInterestService(pointOfInterestRepository,managedExecutorService,MAXIMUM_NUMBER_OF_CHUNKS);
    }
}
