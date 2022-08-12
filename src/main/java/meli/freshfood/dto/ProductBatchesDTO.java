package meli.freshfood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductBatchesDTO {
	private Long productid;
	private List<BatchDetailsDTO> batchStock;
}
