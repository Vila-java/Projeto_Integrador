package meli.freshfood.model;

public enum StatusPurchaseOrder {

    DISABLED("DISABLED"),
    ACTIVATED("ACTIVATED");

    private final String description;

    StatusPurchaseOrder(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

}
