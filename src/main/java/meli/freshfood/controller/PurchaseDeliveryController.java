package meli.freshfood.controller;

import meli.freshfood.dto.PurchaseDeliveryDTO;
import meli.freshfood.model.PurchaseDelivery;
import meli.freshfood.service.PurchaseDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fresh-products/purchasedelivery")
public class PurchaseDeliveryController {

    @Autowired
    private PurchaseDeliveryService purchaseDeliveryService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public PurchaseDelivery save(@RequestBody PurchaseDeliveryDTO purchaseDelivery) {
        return purchaseDeliveryService.save(purchaseDelivery);
    }
}
