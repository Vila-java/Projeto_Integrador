package meli.freshfood.service;

import meli.freshfood.model.Product;

import java.util.Optional;

public interface ProductService {
    Optional<Product> findById(Long id);
}
