package meli.freshfood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * The type Product batches dto.
 */
@Getter
@AllArgsConstructor
public class ProductBatchesDTO {
	private Long productid;
	private List<BatchDetailsDTO> batchStock;
}
