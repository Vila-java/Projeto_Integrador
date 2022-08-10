package meli.freshfood.service;

import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Product;
import meli.freshfood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("O produto não foi encontrado!"));
    }

    // TODO se tiver muito grande a visualização no retorno criar um DTO especifico
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
       List<Product> listProducts = productRepository.findByStorageType(storageType);
        if(listProducts.isEmpty())  {
            throw new NotFoundException("A lista de produtos não foi encontrada!");
        }
        return listProducts;
    }
}