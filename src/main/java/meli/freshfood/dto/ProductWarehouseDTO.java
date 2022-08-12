package meli.freshfood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import meli.freshfood.model.Warehouse;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductWarehouseDTO {
    private Long productId;
    List<WarehouseDTO> warehousesDTO;
}
