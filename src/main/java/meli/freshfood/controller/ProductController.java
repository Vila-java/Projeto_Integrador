package meli.freshfood.controller;

import meli.freshfood.dto.ProductWarehouseDTO;
import meli.freshfood.model.Product;
import meli.freshfood.service.ProductService;
import meli.freshfood.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Product controller.
 */
@RestController
@RequestMapping("/api/v1/fresh-products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private WarehouseService warehouseService;

    /**
     * Find all response entity.
     *
     * @return the response entity
     */
//retorna uma lista completa de prodrutos
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    /**
     * Find product by category response entity.
     *
     * @param storageType the storage type
     * @return the response entity
     */
//retorna uma lista completa de prodrutos filtrados por categoria
    @GetMapping("/list")
    public ResponseEntity<List<Product>> findProductByCategory(
            @RequestParam(required = false) String storageType
    ) {
        return ResponseEntity.ok(productService.findProductByCategory(storageType));
    }

    /**
     * Find by product response entity.
     *
     * @param productId the product id
     * @return the response entity
     */
    @GetMapping("/warehouse")
    public ResponseEntity<ProductWarehouseDTO> findByProduct(@RequestParam Long productId){
        return ResponseEntity.ok(warehouseService.findByProduct(productId));
    }
}
