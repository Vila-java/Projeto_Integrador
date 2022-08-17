package meli.freshfood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Warehouse dto.
 */
@Getter
@Setter
@AllArgsConstructor
public class WarehouseDTO {
    private Long warehouseCode;
    private Integer totalQuantity;
}
