package meli.freshfood.controller;

import meli.freshfood.dto.InboundOrderDTO;
import meli.freshfood.model.InboundOrder;
import meli.freshfood.service.InboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class InboundOrderController {

    @Autowired
    private InboundOrderService service;

    @PostMapping("/inboundorder")
    public ResponseEntity<InboundOrder> create(@RequestBody InboundOrderDTO inboundOrderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(inboundOrderDTO));
    }

    @PutMapping("/inboundorder")
    public ResponseEntity<InboundOrder> update(@RequestBody InboundOrderDTO inboundOrderDTO) {
        return ResponseEntity.ok(service.update(inboundOrderDTO));
    }


}
