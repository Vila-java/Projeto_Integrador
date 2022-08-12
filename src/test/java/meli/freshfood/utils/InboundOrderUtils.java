package meli.freshfood.utils;


import meli.freshfood.dto.BatchDTO;
import meli.freshfood.dto.InboundOrderDTO;
import meli.freshfood.dto.SectionDTO;
import meli.freshfood.model.*;

import java.time.LocalDate;
import java.util.List;

public class InboundOrderUtils {

        public static InboundOrder newInboundOrder(Supervisor supervisor, Section section){
                return new InboundOrder(1L, LocalDate.of(2022, 01, 1), null, supervisor, section);
        }

        public static InboundOrderDTO newInboundOrderDTO(Supervisor supervisor, SectionDTO sectionDTO, List<BatchDTO> batchList){
                return new InboundOrderDTO(1L, supervisor.getSupersivorId(),
                        LocalDate.of(2022, 01, 1), sectionDTO, batchList);
        }
}
