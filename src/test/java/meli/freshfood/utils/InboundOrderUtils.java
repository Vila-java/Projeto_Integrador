package meli.freshfood.utils;


import meli.freshfood.model.*;

import java.time.LocalDate;

public class InboundOrderUtils {

        public static InboundOrder newInboundOrder(Supervisor supervisor, Section section){
                return new InboundOrder(1L, LocalDate.of(2022, 01, 1), null, supervisor, section);
        }

        public static InboundOrder newInboundOrderDTO(Supervisor supervisor, Section section){
                return new InboundOrder(1L, LocalDate.of(2022, 01, 1), null, supervisor, section);
        }

}
