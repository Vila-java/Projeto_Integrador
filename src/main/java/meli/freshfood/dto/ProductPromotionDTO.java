package meli.freshfood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductPromotionDTO {
    private Long productId;
    private String productName;
    private LocalDate dueDate;
    private BigDecimal price;
    private BigDecimal pricePromotion;
    private String message;
}
