package meli.freshfood.dto;

import lombok.Getter;
import meli.freshfood.model.Warehouse;

import java.util.List;

@Getter

public class ProductWarehouseDTO {
    private Long productId;
    List<WarehouseDTO> warehousesDTO;
}
