package meli.freshfood.service;

import meli.freshfood.dto.*;
import meli.freshfood.model.ProductReview;

import java.util.List;

public interface ProductReviewService {
    ProductReview findById(Long productReviewId);
    ProductReview create(ProductReviewCreateDTO productReview);
    ProductReview update(Long productReviewId, ProductReviewUpdateDTO productReview);
    List<ProductReview> findAllWithFilter(Long productId, Long clientId);
    ProductReviewDTO productReviewOfProduct(Long productId);
    List<ProductReviewClientDTO> clientReviews(Long clientId);
    List<ProductReviewProductDTO> reviewsByProduct();
    void delete(Long productReviewId);
}
