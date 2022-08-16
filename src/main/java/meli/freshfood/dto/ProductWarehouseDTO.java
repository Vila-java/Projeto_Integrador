package meli.freshfood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductWarehouseDTO {
    private Long productId;
    List<WarehouseDTO> warehousesDTO;
}
