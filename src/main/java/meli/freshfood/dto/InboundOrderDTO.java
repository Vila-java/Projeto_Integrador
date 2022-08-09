package meli.freshfood.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class InboundOrderDTO {
    private Long orderNumber;
    private Long supervisorId;
    private LocalDate orderDate;
    private SectionDTO section;
    private List<BatchDTO> batchStock;
}
