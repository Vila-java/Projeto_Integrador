package meli.freshfood.repository;

import meli.freshfood.dto.ProductReviewClientDTO;
import meli.freshfood.model.Client;
import meli.freshfood.model.Product;
import meli.freshfood.model.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
    List<ProductReview> findAllByProduct(Product product);
    List<ProductReview> findAllByClient(Client client);
}
