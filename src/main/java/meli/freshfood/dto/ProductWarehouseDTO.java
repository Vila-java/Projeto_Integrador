package meli.freshfood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * The type Product warehouse dto.
 */
@Getter
@AllArgsConstructor
public class ProductWarehouseDTO {
    private Long productId;
    /**
     * The Warehouses dto.
     */
    List<WarehouseDTO> warehousesDTO;
}
