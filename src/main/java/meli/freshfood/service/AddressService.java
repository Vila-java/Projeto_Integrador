package meli.freshfood.service;

import meli.freshfood.dto.AddressDTO;

public interface AddressService {
    AddressDTO findByZipCode(String zipCode);
}
