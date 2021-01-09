package nearby.poi.soap.cache;

import nearby.poi.domain.PointOfInterest;
import nearby.poi.service.PointOfInterestService;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Startup
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class PointOfInterestCache {

    private List<PointOfInterest> pointOfInterestList;
    private Map<PointOfInterest,Integer> countOfNearbyPointOfInterests;

    @Inject
    private PointOfInterestService pointOfInterestService;

    @PostConstruct
    public void initialize(){
        pointOfInterestList = pointOfInterestService.getAllPointOfInterest();
        countOfNearbyPointOfInterests = new HashMap<>();
    }

    @Lock(LockType.READ)
    public List<PointOfInterest> getPointOfInterestList() {
        return pointOfInterestList;
    }

    @Lock(LockType.WRITE)
    public void increaseCounterOfNearbyPointOfInterest(PointOfInterest poi){
        if(countOfNearbyPointOfInterests.containsKey(poi)){
            countOfNearbyPointOfInterests.merge(poi,1,Integer::sum);
        }else{
            countOfNearbyPointOfInterests.put(poi,1);
        }
    }

    @Lock(LockType.READ)
    public Map<PointOfInterest, Integer> getCountOfNearbyPointOfInterests() {
        return countOfNearbyPointOfInterests;
    }

    public void setCountOfNearbyPointOfInterests(Map<PointOfInterest, Integer> countOfNearbyPointOfInterests) {
        this.countOfNearbyPointOfInterests = countOfNearbyPointOfInterests;
    }
}
