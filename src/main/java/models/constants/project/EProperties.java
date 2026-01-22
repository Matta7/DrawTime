package models.constants.project;

public enum EProperties {
    APPLICATION_NAME("app.name"),
    APPLICATION_VERSION("app.version"),;


    private final String property;

    EProperties(String property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return property;
    }
}
