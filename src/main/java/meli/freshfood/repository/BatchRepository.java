package meli.freshfood.repository;

import meli.freshfood.model.Batch;
import meli.freshfood.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
    List<Batch> findAllByProduct(Product product);
}
