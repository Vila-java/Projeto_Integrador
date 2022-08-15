package meli.freshfood.utils;

import meli.freshfood.dto.CarrierDTO;
import meli.freshfood.model.Carrier;
import meli.freshfood.model.Vehicle;

import java.util.ArrayList;
import java.util.List;


public class CarrierUtils {

    static Vehicle vehicle = new Vehicle();

    public static Carrier newCarrier() {

        vehicle = new Vehicle(1L, "CARRO", 10);

        return new Carrier(1L, "João", "Flores", "11122233345",
                "joao.flores@mercadolivre", 1122334455L, "01001001",
                "Praça da Sé", 1, "Sé", "São Paulo", "SP", vehicle);
    }

    public static Carrier newCarrierWithParams(long id, String firstName, String lastName, String cpf, String email, Long phoneNumber, String zipCode, String address, Integer number, String neighborhood, String city, String state, Vehicle vehicle) {
        return new Carrier(1L, "João", "Flores", "11122233345",
                "joao.flores@mercadolivre", 1122334455L, "01001001",
                "Praça da Sé", 1, "Sé", "São Paulo", "SP", vehicle);
    }

    public static CarrierDTO newCarrierDTO() {
        return new CarrierDTO(1L, "João", "Flores", "11122233345",
                "joao.flores@mercadolivre", 1122334455L, "01001001",
                "Praça da Sé", 1, "Sé", "São Paulo", "SP", vehicle);

    }

    public static CarrierDTO newCarrierDTOWithParams(long id, String firstName, String lastName, String cpf, String email, Long phoneNumber, String zipCode, String address, Integer number, String neighborhood, String city, String state) {
        return new CarrierDTO(1L, "João", "Flores", "11122233345",
                "joao.flores@mercadolivre", 1122334455L, "01001001",
                "Praça da Sé", 1, "Sé", "São Paulo", "SP", vehicle);
    }

    public static List<CarrierDTO> carrierListDTO() {
        List<CarrierDTO> carrierListDTO = new ArrayList<>();
        carrierListDTO.add(newCarrierDTOWithParams(1L, "João", "Flores", "11122233345",
                "joao.flores@mercadolivre", 1122334455L, "01001001",
                "Praça da Sé", 1, "Sé", "São Paulo", "SP"));
        return carrierListDTO;
    }

    public static List<Carrier> carrierList() {
        vehicle = new Vehicle(1L, "CARRO", 10);

        List<Carrier> carrierList = new ArrayList<>();
        carrierList.add(newCarrierWithParams(1L, "João", "Flores", "11122233345",
                "joao.flores@mercadolivre", 1122334455L, "01001001",
                "Praça da Sé", 1, "Sé", "São Paulo", "SP", vehicle));
        return carrierList;
    }
}







