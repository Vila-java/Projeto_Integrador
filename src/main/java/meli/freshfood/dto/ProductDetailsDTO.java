package meli.freshfood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * The type Product details dto.
 */
@Getter
@Setter
@AllArgsConstructor
public class ProductDetailsDTO {
    private Long productId;
    private String name;
    private Integer quantity;
    private BigDecimal price;
}
