package meli.freshfood.controller;

import meli.freshfood.model.Product;
import meli.freshfood.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class ProductController {

    @Autowired
    private ProductService productService;


    //retorna uma lista completa de prodrutos
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    //retorna uma lista completa de prodrutos filtrados por categoria
    @GetMapping("/list")
    public ResponseEntity<List<Product>> findProductByCategory(@RequestParam(required = false) String storageType) {
        return ResponseEntity.ok(productService.findProductByCategory(storageType));
    }
}
