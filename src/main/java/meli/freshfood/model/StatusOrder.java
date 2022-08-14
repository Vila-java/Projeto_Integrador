package meli.freshfood.model;

public enum StatusOrder {

    PENDING("PENDING"),
    APPROVED("APPROVED"),
    SENDED("SENDED"),
    DELIVERED("DELIVERED"),
    CANCELED("CANCELED");


    private final String description;

    StatusOrder(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
