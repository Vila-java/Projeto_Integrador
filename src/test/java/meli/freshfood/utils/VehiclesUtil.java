package meli.freshfood.utils;


import meli.freshfood.model.Vehicle;

public class VehiclesUtil {

    public static Vehicle newVehicle() {
        return new Vehicle(1L, "CARRO", 10);
    }
}
