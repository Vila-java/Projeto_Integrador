package meli.freshfood.controller;


import meli.freshfood.dto.ProductDetailsDTO;
import meli.freshfood.dto.PurchaseOrderDTO;
import meli.freshfood.model.Product;
import meli.freshfood.model.ProductPurchaseOrder;
import meli.freshfood.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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
    @GetMapping("/orders/{purchaseOrderId}")
    public ResponseEntity<List<ProductDetailsDTO>> findAllProducts(@PathVariable Long purchaseOrderId) {
        return ResponseEntity.ok(purchaseOrderService.findAllProducts(purchaseOrderId));
    }
    
    //Modifique o pedido existente para torná-lo do tipo de carrinho ABERTO/FINALIZADO
    @PutMapping("/{id}")
    public ResponseEntity<String> closePurchaseOrder(@PathVariable Long id)  {
        purchaseOrderService.closePurchaseOrder(id);
        return ResponseEntity.ok("Carrinho de compras foi finalizado com sucesso!");
    }
    
}




