package meli.freshfood.utils;

import meli.freshfood.dto.CarrierDTO;
import meli.freshfood.model.Carrier;

import java.util.ArrayList;
import java.util.List;


public class CarrierUtils {

    public static Carrier newCarrier() {
        return new Carrier(1L, "João", "Flores", "11122233345",
                "joao.flores@mercadolivre", 1122334455L, "01001001",
                "Praça da Sé", 1, "Sé", "São Paulo", "SP");
    }

    public static Carrier newCarrierWithParams(long id, String firstName, String lastName, String cpf, String email, Long phoneNumber, String zipCode, String address, Integer number, String neighborhood, String city, String state) {
        return new Carrier(1L, "João", "Flores", "11122233345",
                "joao.flores@mercadolivre", 1122334455L, "01001001",
                "Praça da Sé", 1, "Sé", "São Paulo", "SP");
    }

    public static CarrierDTO newCarrierDTO() {
        return new CarrierDTO(1L, "Rosa", "Maria", "11122233345",
                "rosa.maria@mercadolivre", 1122334455L, "01001001",
                "Praça da Sé", 1, "Sé", "São Paulo", "SP");

    }

    public static List<Carrier> carrierList(){
        List<Carrier> carrierList = new ArrayList<>();
        carrierList.add(newCarrierWithParams(1L, "João", "Flores", "11122233345",
                "joao.flores@mercadolivre", 1122334455L, "01001001",
                "Praça da Sé", 1, "Sé", "São Paulo", "SP"));
      return carrierList;
    }
}






