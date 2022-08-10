package meli.freshfood.controller;


import meli.freshfood.dto.PurchaseOrderDTO;
import meli.freshfood.model.Product;
import meli.freshfood.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/fresh-products")
public class PurchaseOrderController {


    @Autowired
    private PurchaseOrderService purchaseOrderService;


    //registre um pedido de compra e retorna o valor total do carrinho
    @PostMapping("/orders")
    public ResponseEntity<Double> create(@RequestBody PurchaseOrderDTO purchaseOrderDTO)    {
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseOrderService.create(purchaseOrderDTO));
    }

    //mostrar produtos no pedido
    @GetMapping("/orders")
    public ResponseEntity<List<Product>> findAllProducts(@RequestBody Long purchaseOrderId) {
        return ResponseEntity.ok(purchaseOrderService.findAllProducts(purchaseOrderId));
    }
    
    //Modifique o pedido existente para torná-lo do tipo de carrinho ABERTO/FINALIZADO
    @PutMapping("/{id}")
    public ResponseEntity<String> closePurchaseOrder(@RequestParam Long id)  {
        purchaseOrderService.closePurchaseOrder(id);
        return ResponseEntity.ok("Produto atualizado com sucesso!");
    }
    
}




