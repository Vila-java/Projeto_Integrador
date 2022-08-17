package meli.freshfood.repository;

import meli.freshfood.model.Product;
import meli.freshfood.model.StorageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Product repository.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * Find by storage type list.
     *
     * @param storageType the storage type
     * @return the list
     */
    List<Product> findByStorageType(StorageType storageType);
}
