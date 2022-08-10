package meli.freshfood.controller;

import meli.freshfood.dto.ProductDTO;
import meli.freshfood.dto.PurchaseOrderDTO;
import meli.freshfood.model.Product;
import meli.freshfood.model.PurchaseOrder;
import meli.freshfood.service.ProductService;
import meli.freshfood.service.PurchaseOrderService;
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


    @Autowired
    private ProductService productService;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    //veja uma lista completa de produtos
    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        return (ResponseEntity<List<ProductDTO>>) ResponseEntity.status(HttpStatus.CREATED);
    }

 /*   //veja uma lista de produtos filtrados por categoria fresco, refrigerado, congelado
    @GetMapping("/list")
    public ResponseEntity<List<ProductDTO>> findByCategory(@RequestParam(required = false) String storageType) {
        List<ProductDTO> list = productService.
        return null;
    }

    //registre um pedido de compra
    @PostMapping("/orders")
    public ResponseEntity<List<Product>>(@RequestBody PurchaseOrder purchaseOrder)    {
        return ResponseEntity.status(HttpStatus.CREATED);
    }
*/

    //mostrar produtos no pedido
    @GetMapping("/orders/id")
    public ResponseEntity<List<PurchaseOrder>> findAllPurchaseOrder() {
        return (ResponseEntity<List<PurchaseOrder>>) ResponseEntity.status(HttpStatus.CREATED);
    }

    //Modifique o pedido existente para torná-lo do tipo de carrinho ABERTO/FINALIZADO
    @PutMapping("/{id}")
    public ResponseEntity<PurchaseOrder> statusPurchase(@RequestBody PurchaseOrderDTO purchaseOrderDTO, @RequestParam Long id)  {
        return new ResponseEntity<PurchaseOrder>(purchaseOrderService.statusPurchase(purchaseOrderDTO, id), HttpStatus.ACCEPTED);
    }
}




