package meli.freshfood.utils;

import meli.freshfood.dto.CarrierDTO;
import meli.freshfood.model.Carrier;


public class CarrierUtils {

    public static Carrier newCarrier(long l, String joão, String flores, String s, String s1, long l1, String s2, String praça_da_sé, String s3, String sé, String são_paulo, String sp) {
        return new Carrier(1L, "João", "Flores", "11122233345",
                "joao.flores@mercadolivre", 1122334455L, "01001001",
                "Praça da Sé", "1", "Sé", "São Paulo", "SP");
    }

    public static CarrierDTO newCarrierDTO() {
        return new CarrierDTO(1L, "Rosa", "Maria", "11122233345",
                "rosa.maria@mercadolivre", 1122334455L, "01001001",
                "Praça da Sé", "1", "Sé", "São Paulo", "SP");

    }

//    public static List<Carrier> carrierList(){
//        List<Carrier> carrierList = new ArrayList<>();
//        carrierList.add(newCarrier(1L, "João", "Flores", "11122233345",
//                "joao.flores@mercadolivre", 1122334455L, "01001001",
//                "Praça da Sé", "1", "Sé", "São Paulo", "SP");
//      return carrierList;
//    }
}






