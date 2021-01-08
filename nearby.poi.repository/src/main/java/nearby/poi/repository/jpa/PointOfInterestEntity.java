package nearby.poi.repository.jpa;

import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="POINT_OF_INTEREST")
public class PointOfInterestEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID",length = 20)
    private Long id;

    @Column(name = "POINT_OF_INTEREST_NAME")
    private String pointOfInterestName;

    @Column(name="POINT_LOCATION")
    private Point pointLocation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPointOfInterestName() {
        return pointOfInterestName;
    }

    public void setPointOfInterestName(String pointOfInterestName) {
        this.pointOfInterestName = pointOfInterestName;
    }

    public Point getPointLocation() {
        return pointLocation;
    }

    public void setPointLocation(Point pointLocation) {
        this.pointLocation = pointLocation;
    }
}
