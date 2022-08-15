package meli.freshfood.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductPromotionDTO {
    private Long productId;
    private String productName;
    private LocalDate dueDate;
    private BigDecimal price;
    private BigDecimal pricePromotion;
    private String message;
}
