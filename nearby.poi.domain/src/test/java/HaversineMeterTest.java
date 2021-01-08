import nearby.poi.domain.LatLongDTO;
import nearby.poi.service.HavershineMetric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class HaversineMeterTest {

    private static final double DISTANCE = 347.3;
    private static LatLongDTO nebraskaPosition;
    private static LatLongDTO kansasPosition;


    @BeforeAll
    public static void setUp(){
        nebraskaPosition = new LatLongDTO(BigDecimal.valueOf(41.507483),BigDecimal.valueOf(-99.436554));
        kansasPosition = new LatLongDTO(BigDecimal.valueOf(38.504048),BigDecimal.valueOf(-98.315949));
    }

    @Test
    public void testHaversineMetric(){
        HavershineMetric havershineMetric = new HavershineMetric();
        BigDecimal distance = havershineMetric.distance(nebraskaPosition, kansasPosition);
        Assertions.assertNotNull(distance);
        Assertions.assertEquals(distance.setScale(1,BigDecimal.ROUND_DOWN),BigDecimal.valueOf(DISTANCE));
    }

}
