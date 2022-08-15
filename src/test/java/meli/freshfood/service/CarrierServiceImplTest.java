package meli.freshfood.service;

import meli.freshfood.dto.CarrierDTO;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Carrier;
import meli.freshfood.model.Vehicle;
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
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


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
    @DisplayName("Retornar o Carrier quando ele for salvo")
    void save_returnCarrier_WhenNewCarrier() {
        BDDMockito.when((carrierRepository.save(ArgumentMatchers.any(Carrier.class))))
                .thenReturn(CarrierUtils.newCarrier());

        CarrierDTO carrierDTO = CarrierUtils.newCarrierDTO();
        CarrierDTO savedCarrierDTO = carrierServiceImpl.save(carrierDTO);

        assertThat(savedCarrierDTO.getId()).isPositive();
        assertThat(savedCarrierDTO.getFirstName()).isEqualTo(savedCarrierDTO.getFirstName());
    }

    @Test
    @DisplayName("Retornar o Carrier quando ele existir")
    void findById_returnCarrier_WhenCarrierIdExists() {
        Optional<Carrier> carrierMocked = Optional.of(CarrierUtils.newCarrier());

        BDDMockito.when(carrierRepository.findById(anyLong())).thenReturn(carrierMocked);

        CarrierDTO carrierdto = carrierServiceImpl.findById(anyLong());

        assertThat(carrierdto.getId()).isPositive();
        assertThat(carrierdto.getClass()).isEqualTo(CarrierDTO.class);
    }

    @Test
    @DisplayName("Retornar uma exceção quando o Carrier não existir")
    void findById_returnNotFoundExcepetion_WhenCarrierNotExists() {
        BDDMockito.when(carrierRepository.findById(anyLong())).thenThrow(new NotFoundException("O carrier não foi encontrado!"));

        Exception exception = assertThrows(
                NotFoundException.class,
                () -> carrierServiceImpl.findById(anyLong())
        );

        assertThat(exception.getMessage()).isEqualTo("O carrier não foi encontrado!");
    }

    @Test
    @DisplayName("Retornar uma lista de Carriers quando os Carriers existirem")
    void findaAll_returnListCarriers_WhenCarriersListExists() {
        List<Carrier> listCarrierMocked = CarrierUtils.carrierList();
        BDDMockito.when(carrierRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"))).thenReturn(listCarrierMocked);

        List<CarrierDTO> carrierDTOList = carrierServiceImpl.findaAll();
        List<CarrierDTO> expectedCarrierDTOList = CarrierUtils.carrierListDTO();

        assertThat(carrierDTOList).isNotNull();
        assertThat(carrierDTOList.size()).isEqualTo(expectedCarrierDTOList.size());

    }

    @Test
    @DisplayName("Alterar um Carrier quando o Carrier existir")
    void updateById_returnCarrierEdit_WhenCarrierExists() {
        Optional<Carrier> carrierMocked = Optional.of(CarrierUtils.newCarrier());
        BDDMockito.when(carrierRepository.findById(anyLong())).thenReturn(carrierMocked);

        CarrierDTO carrierDTO = CarrierUtils.newCarrierDTO();

        Carrier carrier = CarrierUtils.newCarrier();
       // BDDMockito.when(carrierRepository.save(carrier)).thenReturn(carrier);

        BDDMockito.when((carrierRepository.save(ArgumentMatchers.any(Carrier.class))))
                .thenReturn(carrier);

        carrier.setFirstName("Altere o nome");
        carrierDTO.setFirstName("Altere o nome");

        carrierServiceImpl.updateById(carrierDTO);

        assertThat(carrierDTO.getFirstName()).isEqualTo(carrier.getFirstName());

    }

    @Test
    @DisplayName("Deletar um Carrier quando ele existir")
    void deleteById_returnRemoDeleteCarrier_WhenCarrierExists() {
        Optional<Carrier> carrierMocked = Optional.of(CarrierUtils.newCarrier());
        BDDMockito.when(carrierRepository.findById(anyLong())).thenReturn(carrierMocked);

        Long carrier = CarrierUtils.newCarrier().getId();

        BDDMockito.willDoNothing().given(carrierRepository).deleteById(anyLong());

        carrierRepository.deleteById(carrier);

        verify(carrierRepository, only()).deleteById(carrier);
    }

    @Test
    @DisplayName("Retornar o Carrier com o veiculo consultado quando ele existir")
    void findByVehicle_returnCarrier_WhenCarrierIdExists() {
        List<Carrier> carriersMocked = Optional.of(CarrierUtils.carrierList()).get();
        Vehicle vehicle = new Vehicle(1L, "CARRO", 10);
        BDDMockito.when(carrierRepository.findByVehicle(vehicle)).thenReturn(carriersMocked);

        List<Carrier> carrierList = carrierRepository.findByVehicle(vehicle);

        assertThat(carrierList).isNotNull();
        assertThat(carrierList.size()).isEqualTo(carriersMocked.size());
    }
}

