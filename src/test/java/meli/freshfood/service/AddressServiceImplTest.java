package meli.freshfood.service;

import meli.freshfood.client.ViaCepClient;
import meli.freshfood.dto.AddressDTO;
import meli.freshfood.dto.CarrierDTO;
import meli.freshfood.model.Carrier;
import meli.freshfood.utils.CarrierUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AddressServiceImplTest {

    @InjectMocks
    private AddressServiceImpl addressServiceImpl;

    @Autowired
    private ViaCepClient viaCepClient;

    @Test
    @DisplayName("Retornar o ZipCode")
    void findByZipCode_returnZipCode_WhenZipCodeExists() {
        String zipCode = "111";
//      AddressDTO addressDTO = new AddressDTO(01001001", "Praça da Sé", "Sé", "São Paulo", "SP");
       AddressDTO addressDTO = addressServiceImpl.findByZipCode(zipCode);

       assertThat(addressDTO.getZipCode()).isNotNull();

    }
}