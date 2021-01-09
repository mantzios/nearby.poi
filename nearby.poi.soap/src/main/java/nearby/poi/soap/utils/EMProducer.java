package nearby.poi.soap.utils;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class EMProducer {

    @Produces
    @PersistenceContext(unitName = "poiPU")
    private EntityManager em;

}
