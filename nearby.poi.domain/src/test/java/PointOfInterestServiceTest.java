import nearby.poi.domain.LatLongDTO;
import nearby.poi.domain.PointOfInterest;
import nearby.poi.exceptions.PointOfInterestNotFoundException;
import nearby.poi.exceptions.ValidationException;
import nearby.poi.interfaces.PointOfInterestRepository;
import nearby.poi.service.PointOfInterestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PointOfInterestServiceTest {

    private static PointOfInterestService pointOfInterestService;


    @BeforeAll
    static void init() {
        PointOfInterestRepository repo = mock(PointOfInterestRepository.class);
        when(repo.findAllPointsOfInterest()).thenReturn(getMockedResults());
        pointOfInterestService = new PointOfInterestService(repo, Executors.newFixedThreadPool(10), 3);
    }

    @Test
    public void testGetAllPointOfInterests() {
        List<PointOfInterest> allPointOfInterest = pointOfInterestService.getAllPointOfInterest();
        Assertions.assertNotNull(allPointOfInterest);
        Assertions.assertEquals(POIEnum.values().length, allPointOfInterest.size());
    }

    @Test
    public void testFindingClosestPointOfInterestWhenCurrentIsPaiania() throws PointOfInterestNotFoundException {
        LatLongDTO currentPosition = new LatLongDTO(BigDecimal.valueOf(37.95527), BigDecimal.valueOf(23.85443));
        PointOfInterest closestPointOfInterest = pointOfInterestService.findClosestPointOfInterest(pointOfInterestService.getAllPointOfInterest(), currentPosition);
        Assertions.assertNotNull(closestPointOfInterest);
        Assertions.assertEquals(POIEnum.ATHENS_AIRPORT.getName(), closestPointOfInterest.getName());
    }

    @Test
    public void testFindingClosestPointOfInterestWhenCurrentIsKallithea() throws PointOfInterestNotFoundException {
        LatLongDTO currentPosition = new LatLongDTO(BigDecimal.valueOf(37.955894), BigDecimal.valueOf(23.702099));
        PointOfInterest closestPointOfInterest = pointOfInterestService.findClosestPointOfInterest(pointOfInterestService.getAllPointOfInterest(), currentPosition);
        Assertions.assertNotNull(closestPointOfInterest);
        Assertions.assertEquals(POIEnum.ACROPOLIS.getName(), closestPointOfInterest.getName());
    }

    @Test
    public void testFindingClosestPointOfInterestWhenCurrentIsHeraclion() throws PointOfInterestNotFoundException {
        LatLongDTO currentPosition = new LatLongDTO(BigDecimal.valueOf(35.341846), BigDecimal.valueOf(25.148254));
        PointOfInterest closestPointOfInterest = pointOfInterestService.findClosestPointOfInterest(pointOfInterestService.getAllPointOfInterest(), currentPosition);
        Assertions.assertNotNull(closestPointOfInterest);
        Assertions.assertEquals(POIEnum.SANTORINI.getName(), closestPointOfInterest.getName());
    }

    @Test
    public void testFindingClosestPointOfInterestWhenCurrentIsEvosmos() throws PointOfInterestNotFoundException {
        LatLongDTO currentPosition = new LatLongDTO(BigDecimal.valueOf(40.666138), BigDecimal.valueOf(22.903774));
        PointOfInterest closestPointOfInterest = pointOfInterestService.findClosestPointOfInterest(pointOfInterestService.getAllPointOfInterest(), currentPosition);
        Assertions.assertNotNull(closestPointOfInterest);
        Assertions.assertEquals(POIEnum.THESSALONIKI.getName(), closestPointOfInterest.getName());
    }

    @Test
    public void testFindingClosestPointOfInterestWhenCurrentIsLarissa() throws PointOfInterestNotFoundException {
        LatLongDTO currentPosition = new LatLongDTO(BigDecimal.valueOf(39.643452), BigDecimal.valueOf(22.413208));
        PointOfInterest closestPointOfInterest = pointOfInterestService.findClosestPointOfInterest(pointOfInterestService.getAllPointOfInterest(), currentPosition);
        Assertions.assertNotNull(closestPointOfInterest);
        Assertions.assertEquals(POIEnum.MOUNT_OLYMPUS.getName(), closestPointOfInterest.getName());
    }

    @Test
    public void testNoClosestPointOfInterestFound() {
        PointOfInterestRepository repo = mock(PointOfInterestRepository.class);
        when(repo.findAllPointsOfInterest()).thenReturn(new ArrayList<>());
        PointOfInterestService pointOfInterestService = new PointOfInterestService(repo, Executors.newFixedThreadPool(10), 3);

        LatLongDTO currentPosition = new LatLongDTO(BigDecimal.valueOf(39.643452), BigDecimal.valueOf(22.413208));

        PointOfInterestNotFoundException pointOfInterestNotFoundException = Assertions.assertThrows(PointOfInterestNotFoundException.class, () -> {
            pointOfInterestService.findClosestPointOfInterest(pointOfInterestService.getAllPointOfInterest(), currentPosition);
        });
        Assertions.assertNotNull(pointOfInterestNotFoundException.getMessage());
    }

    @Test
    public void testNoLatLongInCurrentPosition() {
        LatLongDTO currentPosition = new LatLongDTO(null, BigDecimal.valueOf(22.413208));
        ValidationException pointOfInterestNotFoundException = Assertions.assertThrows(ValidationException.class, () -> {
            pointOfInterestService.findClosestPointOfInterest(pointOfInterestService.getAllPointOfInterest(), currentPosition);
        });
        Assertions.assertNotNull(pointOfInterestNotFoundException.getMessage());
    }

    private static List<PointOfInterest> getMockedResults() {
        List<PointOfInterest> pointOfInterests = new ArrayList<>();
        for (POIEnum value : POIEnum.values()) {
            PointOfInterest poi = new PointOfInterest(value.getName(),
                    new LatLongDTO(BigDecimal.valueOf(value.getLatitude()), BigDecimal.valueOf(value.getLongitude())));
            pointOfInterests.add(poi);
        }
        return pointOfInterests;
    }

}
