package meli.freshfood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductReviewUpdateDTO {
    private String description;
    private Integer grade;
}
