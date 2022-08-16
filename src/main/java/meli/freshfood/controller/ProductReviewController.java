package meli.freshfood.controller;

import meli.freshfood.dto.*;
import meli.freshfood.model.Product;
import meli.freshfood.model.ProductReview;
import meli.freshfood.service.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product-review")
public class ProductReviewController {
    @Autowired
    ProductReviewService productReviewService;

    @GetMapping
    public ResponseEntity<List<ProductReview>> index(
            @RequestParam(required = false) Long productId,
            @RequestParam(required = false) Long clientId
    ) {
        return ResponseEntity.ok(productReviewService.findAllWithFilter(productId, clientId));
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ProductReview> show(@PathVariable Long reviewId) {
        return ResponseEntity.ok(productReviewService.findById(reviewId));
    }

    @PostMapping
    public ResponseEntity<ProductReview> create(@RequestBody ProductReviewCreateDTO productReview)    {
        return ResponseEntity.status(HttpStatus.CREATED).body(productReviewService.create(productReview));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ProductReview> update(@PathVariable Long reviewId, @RequestBody ProductReviewUpdateDTO productReview)  {
        return ResponseEntity.ok(productReviewService.update(reviewId, productReview));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> delete(@PathVariable Long reviewId)  {
        productReviewService.delete(reviewId);
        return ResponseEntity.ok("Avaliação removida com sucesso");
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductReviewDTO> infoByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(productReviewService.productReviewOfProduct(productId));
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<ProductReviewClientDTO>> reviewsByClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(productReviewService.clientReviews(clientId));
    }

    @GetMapping("/group-by-product")
    public ResponseEntity<List<ProductReviewProductDTO>> reviewsByClient() {
        return ResponseEntity.ok(productReviewService.reviewsByProduct());
    }
}
