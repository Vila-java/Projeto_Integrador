package meli.freshfood.service;

import meli.freshfood.dto.ProductDTO;
import meli.freshfood.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> findById(Long id);
    List<Product> findAll();
    // List<ProductDTO> findByStorageType();
    boolean checkBatchAvailable(ProductDTO productDTO);
    void checkBatchAvailableList(List<ProductDTO> productsDTO);
}
