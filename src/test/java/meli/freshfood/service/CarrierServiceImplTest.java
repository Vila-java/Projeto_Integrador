package meli.freshfood.service;

import meli.freshfood.dto.CarrierDTO;
import meli.freshfood.model.Batch;
import meli.freshfood.model.Carrier;
import meli.freshfood.repository.BatchRepository;
import meli.freshfood.repository.CarrierRepository;
import meli.freshfood.utils.BatchUtils;
import meli.freshfood.utils.CarrierUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CarrierServiceImplTest {

    @InjectMocks
    CarrierServiceImpl carrierService;

    @Mock
    CarrierRepository carrierRepository;

    @Test
    void save() {
    }

    @Test
    void findaAll() {
    }

    @Test
    @DisplayName("Retorna o carrier quando ele existir")
    void findById_returnCarrier_WhenCarrierIdExists() {
        Optional<Carrier> carrierMocked = Optional.of(CarrierUtils.newCarrier());

        BDDMockito.when(carrierRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(carrierMocked);

        CarrierDTO carrierdto = carrierService.findById(ArgumentMatchers.anyLong());

        assertThat(carrierdto.getId()).isPositive();
        assertThat(carrierdto.getClass()).isEqualTo(Carrier.class);
    }


    @Test
    void updateById() {
    }

    @Test
    void deleteById() {
    }
}