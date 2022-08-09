package meli.freshfood.model;


public enum StorageType {

    FROZEN("FROZEN"),
    REFRIGERATED("REFRIGERATED"),
    FRESH("FRESH");

    private final String description;

    StorageType(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
