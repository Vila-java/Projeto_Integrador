package meli.freshfood.controller;

import meli.freshfood.dto.AddressDTO;
import meli.freshfood.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fresh-products/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/{zipCode}")
    public AddressDTO viaCep(@PathVariable String zipCode) {
        return addressService.findByZipCode(zipCode);

    }
}
