import nearby.poi.repository.jpa.PointOfInterestEntity;
import org.hibernate.cfg.AvailableSettings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

public class EntityManagerTest {

    private static EntityManagerFactory entityManagerFactory;
    private static GeometryFactory geometryFactory;

    @BeforeAll
    static void init(){
        Properties generateSchemaProperties = new Properties();
        generateSchemaProperties.setProperty(AvailableSettings.HBM2DDL_AUTO, "create");
        entityManagerFactory = Persistence.createEntityManagerFactory("poiPU",generateSchemaProperties);
        geometryFactory = new GeometryFactory();
    }

    @Test
    public void importData() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            for (POIEnum poi : POIEnum.values()) {
                PointOfInterestEntity pointOfInterestEntity = createPOI(poi.getLatitude(), poi.getLongitude(), poi.getName());
                entityManager.persist(pointOfInterestEntity);
            }
            entityManager.getTransaction().commit();
        }catch(Exception e){
            Assertions.fail();
        }
    }


    private PointOfInterestEntity createPOI(double latitude,double longitude,String name){
        PointOfInterestEntity entity = new PointOfInterestEntity();
        Point point = geometryFactory.createPoint( new Coordinate(latitude,longitude) );
        point.setSRID(4326);
        entity.setPointLocation(point);
        entity.setPointOfInterestName(name);
        return entity;
    }
}
