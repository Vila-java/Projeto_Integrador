package meli.freshfood.repository;

import meli.freshfood.model.Batch;
import meli.freshfood.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Batch repository.
 */
@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
    /**
     * Find all by product list.
     *
     * @param product the product
     * @return the list
     */
    List<Batch> findAllByProduct(Product product);
}
