package meli.freshfood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;


/**
 * The type Batch details dto.
 */
@Getter
@AllArgsConstructor
public class BatchDetailsDTO {

	private Long batchNumber;
	private Float currentTemperature;
	private Integer currentQuantity;
	private LocalDate dueDate;
	private Long warehouseId;
	private Long sectionCode;
}