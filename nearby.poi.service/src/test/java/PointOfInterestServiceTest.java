import nearby.poi.domain.LatLongDTO;
import nearby.poi.domain.MetricAlgorithmEnum;
import nearby.poi.domain.PointOfInterest;
import nearby.poi.service.HavershineMetric;
import nearby.poi.service.PointOfInterestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PointOfInterestServiceTest {

    private static LatLongDTO currentPosition;
    private static List<PointOfInterest> pointOfInterests;

    @BeforeAll
    static void init(){
        currentPosition = new LatLongDTO(BigDecimal.valueOf(41.507483),BigDecimal.valueOf(-99.436554));
        pointOfInterests = new ArrayList<>();
    }

    @Test
    public void testFindingClosestPointOfInterest(){
        PointOfInterestService pointOfInterestService = new PointOfInterestService();
        PointOfInterest closestPointOfInterest = pointOfInterestService.findClosestPointOfInterest(pointOfInterests, currentPosition, MetricAlgorithmEnum.HAVERSINE_FORMULA);
        Assertions.assertNotNull(closestPointOfInterest);
    }
}
