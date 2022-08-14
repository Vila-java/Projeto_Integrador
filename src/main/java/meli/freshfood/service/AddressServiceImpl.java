package meli.freshfood.service;

import meli.freshfood.client.ViaCepClient;
import meli.freshfood.client.ViaCepResponse;
import meli.freshfood.dto.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private ViaCepClient viaCepClient;

    @Override
    public AddressDTO findByZipCode(String zipCode) {
        ViaCepResponse viaCepResponse = viaCepClient.findByZipCode(zipCode);
        return AddressDTO.builder()
                .address(viaCepResponse.getLogradouro())
                .zipCode(viaCepResponse.getCep())
                .neighborhood(viaCepResponse.getBairro())
                .city(viaCepResponse.getLocalidade())
                .state(viaCepResponse.getUf())
                .build();
    }
}
