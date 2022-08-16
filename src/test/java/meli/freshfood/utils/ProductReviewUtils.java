package meli.freshfood.utils;

import meli.freshfood.dto.ProductReviewCreateDTO;
import meli.freshfood.dto.ProductReviewUpdateDTO;
import meli.freshfood.model.*;
import net.bytebuddy.asm.Advice;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductReviewUtils {
    public static ProductReview newProductReview() {
        Client client = ClientUtils.newClient();
        Product product = ProductUtils.newProduct();
        return new ProductReview(1L, "descrição", 5,
                LocalDate.now(), LocalDate.now(), client, product);
    }

    public static ProductReview newProductReviewWithProductAndClient(Product product, Client client) {
        return new ProductReview(1L, "descrição", 5,
                LocalDate.now(), LocalDate.now(), client, product);
    }

    public static ProductReviewCreateDTO newProductReviewCreateDTO() {
        Client client = ClientUtils.newClient();
        Product product = ProductUtils.newProduct();
        return new ProductReviewCreateDTO("Descrição", 4, client.getClientId(),
                product.getProductId());
    }

    public static ProductReviewUpdateDTO newProductReviewUpdateDTO(String description, Integer grade) {
        return new ProductReviewUpdateDTO(description, grade);
    }
}
