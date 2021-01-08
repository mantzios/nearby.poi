package nearby.poi.domain;

public enum MetricAlgorithmEnum {

    HAVERSINE_FORMULA("HAVERSINE");

    private String name;

    MetricAlgorithmEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
