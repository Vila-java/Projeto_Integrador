package meli.freshfood.utils;

import meli.freshfood.model.Address;
import meli.freshfood.model.Carrier;

public class CarrierUtils {

    public static Carrier newCarrier() {
        Address address = new Address();
        return new Carrier(1L, "João", "Flores", "11122233345", "joao.flores@mercadolivre", 1122334455L, address);


       // 1L, "01001001", "Praça da Sé", "Sé", "São Paulo/SP", "SP", "null", "null", "null", "null"
    }
}






