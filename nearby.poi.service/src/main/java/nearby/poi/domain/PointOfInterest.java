package nearby.poi.domain;

public class PointOfInterest {

    private String name;
    private LatLongDTO position;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLongDTO getPosition() {
        return position;
    }

    public void setPosition(LatLongDTO position) {
        this.position = position;
    }
}
