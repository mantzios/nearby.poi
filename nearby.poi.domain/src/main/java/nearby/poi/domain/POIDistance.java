package nearby.poi.domain;

import java.math.BigDecimal;

public class POIDistance {

    private PointOfInterest pointOfInterest;
    private BigDecimal distanceFromCurrentPosition;

    public POIDistance(PointOfInterest pointOfInterest, BigDecimal distanceFromCurrentPosition) {
        this.pointOfInterest = pointOfInterest;
        this.distanceFromCurrentPosition = distanceFromCurrentPosition;
    }

    public PointOfInterest getPointOfInterest() {
        return pointOfInterest;
    }

    public void setPointOfInterest(PointOfInterest pointOfInterest) {
        this.pointOfInterest = pointOfInterest;
    }

    public BigDecimal getDistanceFromCurrentPosition() {
        return distanceFromCurrentPosition;
    }

    public void setDistanceFromCurrentPosition(BigDecimal distanceFromCurrentPosition) {
        this.distanceFromCurrentPosition = distanceFromCurrentPosition;
    }
}
