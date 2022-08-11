package meli.freshfood.model;


public enum StorageType {

    FROZEN("FROZEN"),
    REFRIGERATED("REFRIGERATED"),
    FRESH("FRESH");

    private final String description;

    StorageType(String description) {
        this.description = description;
    }

    public static StorageType parseToStorage(String initials) {
        if(initials.equalsIgnoreCase("FR")) {
            return StorageType.FRESH;
        } else if (initials.equalsIgnoreCase("RF")) {
            return StorageType.REFRIGERATED;
        } else if (initials.equalsIgnoreCase("FF")) {
            return StorageType.FROZEN;
        } else {
            return null;
        }
    }
}
