package meli.freshfood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
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

    @Column(precision = 11, scale = 2)
    private BigDecimal pricePromotion;
    private String message;
}
