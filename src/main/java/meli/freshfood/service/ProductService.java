package meli.freshfood.service;

import meli.freshfood.model.Product;
import meli.freshfood.model.Section;

import java.util.List;

/**
 * The interface Product service.
 */
public interface ProductService {

    /**
     * Find by id product.
     *
     * @param id the id
     * @return the product
     */
    Product findById(Long id);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<Product> findAll();

    /**
     * Find product by category list.
     *
     * @param storageType the storage type
     * @return the list
     */
    List<Product> findProductByCategory(String storageType);

    /**
     * Check product storage is equal section storage boolean.
     *
     * @param product the product
     * @param section the section
     * @return the boolean
     */
    Boolean checkProductStorageIsEqualSectionStorage(Product product, Section section);
}
