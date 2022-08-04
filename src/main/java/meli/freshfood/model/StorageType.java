package meli.freshfood.model;


public enum StorageType {

    FROZEN("Frozen"),
    REFRIGERATED("Refrigerated"),
    FRESH("Fresh");

    private final String description;

    StorageType(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
