import nearby.poi.domain.LatLongDTO;
import nearby.poi.domain.MetricAlgorithmEnum;
import nearby.poi.domain.PointOfInterest;
import nearby.poi.service.HavershineMetric;
import nearby.poi.service.PointOfInterestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class PointOfInterestServiceTest {

    private static LatLongDTO currentPosition;
    private static List<PointOfInterest> pointOfInterests;

    @BeforeAll
    static void init() {
        currentPosition = new LatLongDTO(BigDecimal.valueOf(37.983810), BigDecimal.valueOf(23.727539));
        PointOfInterest thessaloniki = new PointOfInterest("thessaloniki",new LatLongDTO(BigDecimal.valueOf(40.629269),BigDecimal.valueOf(22.947412)));
        PointOfInterest berlin = new PointOfInterest("berlin",new LatLongDTO(BigDecimal.valueOf(52.520008),BigDecimal.valueOf(13.404954)));
        PointOfInterest paris = new PointOfInterest("paris",new LatLongDTO(BigDecimal.valueOf(48.864716),BigDecimal.valueOf(2.349014)));
        pointOfInterests = Arrays.asList(thessaloniki,berlin,paris);
    }

    @Test
    public void testFindingClosestPointOfInterest() {
        PointOfInterestService pointOfInterestService = new PointOfInterestService();
        PointOfInterest closestPointOfInterest = pointOfInterestService.findClosestPointOfInterest(pointOfInterests, currentPosition, new HavershineMetric());
        Assertions.assertNotNull(closestPointOfInterest);
        Assertions.assertEquals("thessaloniki",closestPointOfInterest.getName());
    }
}
