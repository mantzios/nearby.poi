package nearby.poi.soap.cache;

import nearby.poi.domain.PointOfInterest;
import nearby.poi.service.PointOfInterestService;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.inject.Inject;
import java.util.List;

@Startup
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class POICache {

    private List<PointOfInterest> pointOfInterestList;

    @Inject
    private PointOfInterestService pointOfInterestService;

    @PostConstruct
    public void initialize(){
        pointOfInterestList = pointOfInterestService.getAllPointOfInterest();
    }

    @Lock(LockType.READ)
    public List<PointOfInterest> getPointOfInterestList() {
        return pointOfInterestList;
    }

    @Lock(LockType.WRITE)
    public void setPointOfInterestList(List<PointOfInterest> pointOfInterestList) {
        this.pointOfInterestList = pointOfInterestList;
    }

}
