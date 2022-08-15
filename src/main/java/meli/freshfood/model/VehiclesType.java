package meli.freshfood.model;

public enum VehiclesType {

    MOTOCICLETA("MOTOCICLETA"),
    CARRO("CARRO"),
    CAMINHAO("CAMINHAO");

    private final String description;

    VehiclesType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
