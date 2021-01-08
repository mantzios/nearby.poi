package nearby.poi.service;

import nearby.poi.domain.LatLongDTO;

import java.math.BigDecimal;

public interface DistanceMetricInterface {

    BigDecimal distance(LatLongDTO pointFrom,LatLongDTO pointTo);

}
