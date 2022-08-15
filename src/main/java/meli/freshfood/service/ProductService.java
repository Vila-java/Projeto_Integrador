package meli.freshfood.service;

import meli.freshfood.model.Product;
import meli.freshfood.model.Section;

import java.util.List;

public interface ProductService {

    Product findById(Long id);
    List<Product> findAll();
    List<Product> findProductByCategory(String storageType);
    Boolean checkProductStorageIsEqualSectionStorage(Product product, Section section);
}
