package meli.freshfood.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PurchaseOrderDTO {
    private LocalDate Date;
    private Long buyerId;
    private String orderStatus;
    private List<ProductDTO> productsDTO;
}