package meli.freshfood.service;

import meli.freshfood.dto.ProductReviewCreateDTO;
import meli.freshfood.dto.ProductReviewUpdateDTO;
import meli.freshfood.model.ProductReview;

import java.util.List;

public interface ProductReviewService {
    ProductReview findById(Long productReviewId);
    List<ProductReview> findAll();
    ProductReview create(ProductReviewCreateDTO productReview);
    ProductReview update(Long productReviewId, ProductReviewUpdateDTO productReview);
    void delete(Long productReviewId);
}
