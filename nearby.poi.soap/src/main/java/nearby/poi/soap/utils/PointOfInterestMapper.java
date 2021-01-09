package nearby.poi.soap.utils;

import nearby.poi.domain.PointOfInterest;
import nearby.poi.soap.dto.PointOfInterestDTO;

public class PointOfInterestMapper {

    public static PointOfInterestDTO convert(PointOfInterest pointOfInterest){
        PointOfInterestDTO pointOfInterestDTO = new PointOfInterestDTO();
        pointOfInterestDTO.setName(pointOfInterest.getName());
        return pointOfInterestDTO;
    }
}
