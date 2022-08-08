package meli.freshfood.dto;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BatchDTO {
	private Long batchNumber;
	private Long productId;
	private Float currentTemperature;
	private Float minimumTemperature;
	private Integer initialQuantity;
	private Integer currentQuantity;
	private LocalDate manufacturingDate;
	private LocalDateTime manufacturingTime;
	private LocalDate dueDate;
}
