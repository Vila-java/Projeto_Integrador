package meli.freshfood.dto;


import lombok.Data;
import meli.freshfood.model.Batch;

import java.time.LocalDate;
import java.util.List;

@Data
public class InboundOrderDTO {
    private Integer orderNumber;
    private LocalDate orderDate;
    private SectionDTO section;
    private List<Batch> batchStock;
}
