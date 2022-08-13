package meli.freshfood.controller;

import meli.freshfood.model.Carrier;
import meli.freshfood.service.CarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fresh-products/carrier")
public class CarrierController {

    @Autowired
    private CarrierService carrierService;


    @PostMapping
    public Carrier save(@Validated @RequestBody Carrier carrier){
        return null;


    }





}
