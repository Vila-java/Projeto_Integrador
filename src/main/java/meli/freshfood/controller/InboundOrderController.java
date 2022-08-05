package meli.freshfood.controller;

import meli.freshfood.dto.InboundOrderDTO;
import meli.freshfood.model.InboundOrder;
import meli.freshfood.service.InboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class InboundOrderController {

    @Autowired
    private InboundOrderService service;

    @PutMapping("/inboundorder")
    public ResponseEntity<InboundOrder> (InboundOrderDTO inboundOrderDTO) {
        return ResponseEntity.created(service.update());

    }


}
