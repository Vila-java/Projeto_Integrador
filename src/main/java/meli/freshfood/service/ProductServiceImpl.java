package meli.freshfood.service;

import meli.freshfood.dto.BatchDetailsDTO;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.*;
import meli.freshfood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("O produto não foi encontrado!"));
    }

    @Override
    public List<Product> findAll() {
        List<Product> listProducts = productRepository.findAll();

        if(listProducts.isEmpty())  {
            throw new NotFoundException("A lista de produtos não foi encontrada!");
        }
        return listProducts;
    }

    @Override
    public List<Product> findProductByCategory(String storageType) {
        StorageType storageTypeObj = StorageType.parseToStorage(storageType);
        List<Product> listProducts = productRepository.findByStorageType(storageTypeObj);
        if(listProducts.isEmpty())  {
            throw new NotFoundException("A lista de produtos não foi encontrada!");
        }
        return listProducts;
    }
}