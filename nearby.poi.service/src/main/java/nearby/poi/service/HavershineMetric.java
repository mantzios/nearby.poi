package nearby.poi.service;

import nearby.poi.domain.LatLongDTO;

import java.math.BigDecimal;

public class HavershineMetric implements DistanceMetricInterface {

    private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

    public static double haversine(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }

    @Override
    public BigDecimal distance(LatLongDTO pointFrom, LatLongDTO pointTo) {
        double latitudeDifference  = Math.toRadians(pointTo.getLatitude().subtract(pointFrom.getLatitude()).doubleValue());
        double longitudeDifference = Math.toRadians(pointTo.getLongitude().subtract(pointFrom.getLongitude()).doubleValue());

        double pointFromLatitude = Math.toRadians(pointFrom.getLatitude().doubleValue());
        double pointEndLatitude = Math.toRadians(pointTo.getLatitude().doubleValue());

        double a = haversine(latitudeDifference) + Math.cos(pointFromLatitude) * Math.cos(pointEndLatitude) * haversine(longitudeDifference);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return BigDecimal.valueOf(EARTH_RADIUS * c); // <-- d
    }
}
