package meli.freshfood.controller;

import meli.freshfood.dto.ProductDTO;
import meli.freshfood.model.Product;
import meli.freshfood.model.PurchaseOrder;
import meli.freshfood.repository.PurchaseOrderRepository;
import meli.freshfood.service.ProductService;
import meli.freshfood.service.PurchaseOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v2/fresh-products")
public class PurchaseOrderController {

/*    @Autowired
    private PurchaseOrderServiceImpl purchaseOrderServiceImpl;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private ProductService productService;

    //veja uma lista completa de produtos
    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        return (ResponseEntity<List<ProductDTO>>) ResponseEntity.status(HttpStatus.CREATED);
    }

    //veja uma lista de produtos filtrados por categoria fresco, refrigerado, congelado
    @GetMapping("/list")
    public ResponseEntity<List<ProductDTO>> findByCategory(@RequestParam(required = false) String storageType) {
        List<ProductDTO> list = productService.


        return null;

    }

    //registre um pedido de compra
    @PostMapping("/orders")
    public ResponseEntity<List<Product>>(@RequestBody PurchaseOrder purchaseOrder) {
        return ResponseEntity.status(HttpStatus.CREATED);
    }

    //mostrar produtos no pedido
    @GetMapping
    public ResponseEntity<List<Product>>(@RequestParam ) {
        return ResponseEntity.status(HttpStatus.CREATED);
    }

    //modifique o pedido existente
    @PutMapping
    public ResponseEntity<List<Product>>(@RequestParam Product product){
        return ResponseEntity.status(HttpStatus.CREATED);
        }*/

}




