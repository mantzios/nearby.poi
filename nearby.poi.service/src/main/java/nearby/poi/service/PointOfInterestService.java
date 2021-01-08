package nearby.poi.service;

import nearby.poi.domain.LatLongDTO;
import nearby.poi.domain.MetricAlgorithmEnum;
import nearby.poi.domain.PointOfInterest;

import java.util.List;

public class PointOfInterestService {


    /**
     * Returns the closest point of interest based on current position
     * @param pointOfInterests the list of point of interests
     * @param currentPosition the current position
     * @param metricAlgorithmEnum
     * @return the closest point of interest
     */
    public PointOfInterest findClosestPointOfInterest(List<PointOfInterest> pointOfInterests, LatLongDTO currentPosition, MetricAlgorithmEnum metricAlgorithmEnum){

        return new PointOfInterest();
    }

}
