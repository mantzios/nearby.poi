package nearby.poi.repository.mapper;

import nearby.poi.domain.LatLongDTO;
import nearby.poi.domain.PointOfInterest;
import nearby.poi.repository.jpa.PointOfInterestEntity;

import java.math.BigDecimal;

public class PointOfInterestMapper {

    public static PointOfInterest convertEntityToPointOfInterest(PointOfInterestEntity entity) {
        PointOfInterest pointOfInterest = new PointOfInterest();
        pointOfInterest.setName(entity.getPointOfInterestName());
        pointOfInterest.setPosition(new LatLongDTO(BigDecimal.valueOf(entity.getPointLocation().getCoordinate().x),
                BigDecimal.valueOf(entity.getPointLocation().getCoordinate().y)));
        return pointOfInterest;
    }
}
