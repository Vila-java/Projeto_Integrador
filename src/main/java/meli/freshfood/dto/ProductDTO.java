package meli.freshfood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Product dto.
 */
@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {
    private Long productId;
    private Integer quantity;
}
