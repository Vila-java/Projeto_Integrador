package meli.freshfood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * The type Product due date dto.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDueDateDTO {
    /**
     * The Batches stocks dto.
     */
    List<BatchStockDTO> batchesStocksDTO;

}
