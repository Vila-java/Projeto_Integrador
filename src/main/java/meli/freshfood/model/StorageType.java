package meli.freshfood.model;


/**
 * The enum Storage type.
 */
public enum StorageType {

    /**
     * Frozen storage type.
     */
    FROZEN("FROZEN"),
    /**
     * Refrigerated storage type.
     */
    REFRIGERATED("REFRIGERATED"),
    /**
     * Fresh storage type.
     */
    FRESH("FRESH");

    private final String description;

    StorageType(String description) {
        this.description = description;
    }

    /**
     * Parse to storage storage type.
     *
     * @param initials the initials
     * @return the storage type
     */
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
