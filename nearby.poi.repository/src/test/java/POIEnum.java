public enum POIEnum {

    ATHENS_AIRPORT("Athens International Airport (ATH), Spata Srtemida, Greece",37.937225, 23.945238),
    MOUNT_OLYMPUS("Mount Olympus, Greece",40.090549, 22.361469),
    SANTORINI("Santorini",36.393154, 25.461510),
    ATHENS("Athens, Greece",37.983810, 23.727539),
    SKG("Thessaloniki Airport Makedonia (SKG), Thessaloníki, Greece",40.524204, 22.976887),
    ACROPOLIS("Acropolis, Athens, Greece",37.970833, 23.726110),
    THESSALONIKI("Thessaloniki, Greece",40.629269, 22.947412),

    ;

    POIEnum(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private String name;
    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }
}
