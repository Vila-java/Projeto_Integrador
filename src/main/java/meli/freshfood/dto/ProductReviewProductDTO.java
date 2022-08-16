package meli.freshfood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class ProductReviewProductDTO {
    private Long productId;
    private Double gradeAverage;
    private Integer totalReviews;
}
