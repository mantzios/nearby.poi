import nearby.poi.repository.jpa.PointOfInterestEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerTest {

    @Test
    public void test() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("poiPU");
        EntityManager entityManager = emf.createEntityManager();
        PointOfInterestEntity entity = new PointOfInterestEntity();

        entityManager.getTransaction().begin();
        GeometryFactory gf = new GeometryFactory();
        Point point = gf.createPoint( new Coordinate(23.444,-53.000) );
        point.setSRID(4326);
        entity.setPointLocation(point);
        entity.setPointOfInterestName("test");
        entityManager.persist(entity);
        entityManager.getTransaction().commit();


        PointOfInterestEntity p1 = entityManager.createQuery("select t from PointOfInterestEntity t ",
                PointOfInterestEntity.class)
                .getResultList().get(0);
        Assertions.assertEquals(p1.getPointLocation().getCoordinate().getX(),23.444);
        Assertions.assertEquals(p1.getPointLocation().getCoordinate().getY(),-53.000);
    }
}
