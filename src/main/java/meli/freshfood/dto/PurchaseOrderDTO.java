package meli.freshfood.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter

public class PurchaseOrderDTO {
    private LocalDate Date;
    private Long buyerId;
    private String orderStatus;
    private List<ProductDTO> products;
}