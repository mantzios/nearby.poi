package nearby.poi.interfaces;

import nearby.poi.domain.PointOfInterest;

import java.util.List;

public interface PointOfInterestRepository {

    List<PointOfInterest> findAllPointsOfInterest();
}
