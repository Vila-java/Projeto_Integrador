package meli.freshfood.controller;

import meli.freshfood.dto.BatchDTO;
import meli.freshfood.dto.InboundOrderDTO;
import meli.freshfood.service.InboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Inbound order controller.
 */
@RestController
@RequestMapping("/api/v1/fresh-products")
public class InboundOrderController {

    @Autowired
    private InboundOrderService service;

    /**
     * Create response entity.
     *
     * @param inboundOrderDTO the inbound order dto
     * @return the response entity
     */
    @PostMapping("/inboundorder")
    public ResponseEntity<List<BatchDTO>> create(@RequestBody InboundOrderDTO inboundOrderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(inboundOrderDTO));
    }

    /**
     * Update response entity.
     *
     * @param inboundOrderDTO the inbound order dto
     * @return the response entity
     */
    @PutMapping("/inboundorder")
    public ResponseEntity<List<BatchDTO>> update(@RequestBody InboundOrderDTO inboundOrderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.update(inboundOrderDTO));
    }

}
