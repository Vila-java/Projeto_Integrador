package meli.freshfood.service;

import meli.freshfood.exception.InternalServerErrorException;
import meli.freshfood.model.Product;
import meli.freshfood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Optional<Product> findById(Long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {

            throw new InternalServerErrorException("O produto precisa ser preenchido");
        }

        return product;
    }
}


