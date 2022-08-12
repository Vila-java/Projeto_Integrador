package meli.freshfood.repository;

import meli.freshfood.model.Product;
import meli.freshfood.model.StorageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStorageType(StorageType storageType);
}
