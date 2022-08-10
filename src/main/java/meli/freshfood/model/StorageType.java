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
        if(initials.equals("FR")) {
            return StorageType.FRESH;
        } else if (initials.equals("RF")) {
            return StorageType.REFRIGERATED;
        } else if (initials.equals("FF")) {
            return StorageType.FROZEN;
        } else {
            return null;
        }
    }
}
