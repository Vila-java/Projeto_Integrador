package meli.freshfood.model;

/**
 * The enum Status purchase order.
 */
public enum StatusPurchaseOrder {

    /**
     * Disabled status purchase order.
     */
    DISABLED("DISABLED"),
    /**
     * Activated status purchase order.
     */
    ACTIVATED("ACTIVATED");

    private final String description;

    StatusPurchaseOrder(String description) {
        this.description = description;
    }

    /**
     * Get description string.
     *
     * @return the string
     */
    public String getDescription(){
        return description;
    }

}
