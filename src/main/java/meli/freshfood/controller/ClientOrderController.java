package meli.freshfood.controller;

import meli.freshfood.dto.ClientOrderDTO;
import meli.freshfood.model.ClientOrder;
import meli.freshfood.service.ClientOrderService;
import meli.freshfood.service.ClientOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
