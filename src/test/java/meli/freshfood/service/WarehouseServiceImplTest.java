package meli.freshfood.service;

import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Warehouse;
import meli.freshfood.repository.WarehouseRepository;
import meli.freshfood.utils.WarehouseUtils;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class WarehouseServiceImplTest {

    @InjectMocks
    WarehouseServiceImpl warehouseService;

    @Mock
    WarehouseRepository warehouseRepository;

    @Test
    @DisplayName("Retorna o armazém quando existir")
    void findById_returnWarehouse_whenWarehouseIdExists() {
        Optional<Warehouse> warehouseMocked = Optional.of(WarehouseUtils.newWarehouseWithoutSupervisor());

        BDDMockito.when(warehouseRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(warehouseMocked);

        Warehouse warehouse= warehouseService.findById(ArgumentMatchers.anyLong());

        assertThat(warehouse.getWarehouseId()).isPositive();
        assertThat(warehouse.getClass()).isEqualTo(Warehouse.class);
    }

    @Test
    @DisplayName("Retorna exceção caso o Id do armazém não exista")
    void returnNotFoundException_whenWarehouseIdNotExists() {
        BDDMockito.when(warehouseRepository.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new NotFoundException("O armazém não foi encontrado!"));

        Exception exception = assertThrows(
                NotFoundException.class,
                () -> warehouseService.findById(ArgumentMatchers.anyLong())
        );

        assertThat(exception.getMessage()).isEqualTo("O armazém não foi encontrado!");
    }

}