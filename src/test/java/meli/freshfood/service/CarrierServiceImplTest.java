package meli.freshfood.service;

import meli.freshfood.dto.CarrierDTO;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Carrier;
import meli.freshfood.repository.CarrierRepository;
import meli.freshfood.utils.CarrierUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CarrierServiceImplTest {

    @InjectMocks
    CarrierServiceImpl carrierServiceImpl;

    @Spy
    ModelMapper modelMapper;

    @Mock
    CarrierRepository carrierRepository;

    @Test
    void save_returnCarrier_WhenNewCarrier() {
        CarrierDTO carrierDTO = CarrierUtils.newCarrierDTO();
        CarrierDTO savedCarrier = carrierServiceImpl.save(carrierDTO);

        assertThat(savedCarrier.getId()).isPositive();
        assertThat(savedCarrier.getFirstName()).isEqualTo(carrierDTO.getFirstName());

    }

    @Test
    @DisplayName("Retorna o carrier quando ele existir")
    void findById_returnCarrier_WhenCarrierIdExists() {
        Optional<Carrier> carrierMocked = Optional.of(CarrierUtils.newCarrier(1L, "João", "Flores", "11122233345", "joao.flores@mercadolivre", 1122334455L, "01001001", "Praça da Sé", "1", "Sé", "São Paulo", "SP"));

        BDDMockito.when(carrierRepository.findById(ArgumentMatchers.anyLong())).thenReturn(carrierMocked);

        CarrierDTO carrierdto = carrierServiceImpl.findById(ArgumentMatchers.anyLong());

        assertThat(carrierdto.getId()).isPositive();
        assertThat(carrierdto.getClass()).isEqualTo(CarrierDTO.class);
    }

    @Test
    @DisplayName("Retorna uma exceção quando o carrier não existir")
    void findById_returnNotFoundExcepetion_WhenCarrierNotExists() {
        BDDMockito.when(carrierRepository.findById(ArgumentMatchers.anyLong())).thenThrow(new NotFoundException("O carrier não foi encontrado!"));

        Exception exception = assertThrows(
                NotFoundException.class,
                () -> carrierServiceImpl.findById(ArgumentMatchers.anyLong())
        );

        assertThat(exception.getMessage()).isEqualTo("O carrier não foi encontrado!");
    }


    @Test
    void updateById() {
    }

    @Test
    void deleteById() {
    }
}