package meli.freshfood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductReviewCreateDTO {
    private String description;
    private Integer grade;
    private Long clientId;
    private Long productId;
}
