package meli.freshfood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class InboundOrderDTO {
    private Long orderNumber;
    private Long supervisorId;
    private LocalDate orderDate;
    private SectionDTO section;
    private List<BatchDTO> batchStock;
}
