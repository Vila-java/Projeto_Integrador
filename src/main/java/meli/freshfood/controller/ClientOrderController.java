package meli.freshfood.controller;

import meli.freshfood.dto.ClientOrderDTO;
import meli.freshfood.model.ClientOrder;
import meli.freshfood.service.ClientOrderService;
import meli.freshfood.service.ClientOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class ClientOrderController {

    @Autowired
    ClientOrderService clientOrderService;

    @GetMapping("/client-order/{id}")
    public ResponseEntity<List<ClientOrderDTO>> getAllOrders(@PathVariable Long id) {
        return ResponseEntity.ok(clientOrderService.getAllOrders(id));
    }

    @PutMapping ("/client-order")
    public ResponseEntity<String> changeOrderStatus(@RequestParam Long id, @RequestParam String status) {
        return ResponseEntity.ok(clientOrderService.changeOrderStatus(id,status));
    }
}
