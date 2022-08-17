package meli.freshfood.service;

import meli.freshfood.dto.ProductWarehouseDTO;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Batch;
import meli.freshfood.model.Product;
import meli.freshfood.model.Warehouse;
import meli.freshfood.repository.ProductRepository;
import meli.freshfood.repository.WarehouseRepository;
import meli.freshfood.utils.BatchUtils;
import meli.freshfood.utils.ProductUtils;
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

import java.util.ArrayList;
import java.util.List;
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

    @Mock
    ProductService productService;

    @Mock
    BatchService batchService;

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

    @Test
    @DisplayName("Retorna os armazéns de um determinado produto agrupados pelo armazém")
    void findByProduct_returnListWarehouseAndProduct_whenProductHaveWarehouse() {
        Product product = ProductUtils.newProduct();
        List<Batch> batches = new ArrayList<>();
        batches.add(BatchUtils.newBatchWithProduct(1L, product));
        batches.add(BatchUtils.newBatchWithProduct(2L, product));

        BDDMockito.when(productService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(product);

        BDDMockito.when(batchService.findAllByProduct(product))
                .thenReturn(batches);

        ProductWarehouseDTO productWarehouseDTO = warehouseService.findByProduct(product.getProductId());

        assertThat(productWarehouseDTO.getClass()).isEqualTo(ProductWarehouseDTO.class);
        assertThat(productWarehouseDTO.getProductId()).isEqualTo(product.getProductId());

        Integer totalBatchesQuantity = batches.stream().mapToInt((b) -> b.getCurrentQuantity()).sum();
        assertThat(productWarehouseDTO.getWarehousesDTO().get(0).getTotalQuantity()).isEqualTo(totalBatchesQuantity);
        Integer numbersOfWarehouse = 1;
        assertThat(productWarehouseDTO.getWarehousesDTO().size()).isEqualTo(numbersOfWarehouse);
    }

    @Test
    @DisplayName("Lança exceção caso produto não exista")
    void findByProduct_returnNotFoundException_whenProductNotExists() {
        BDDMockito.when(productService.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new NotFoundException("O produto não foi encontrado!"));

        Exception exception = assertThrows(
                NotFoundException.class,
                () -> warehouseService.findByProduct(ArgumentMatchers.anyLong())
        );

        assertThat(exception.getMessage()).isEqualTo("O produto não foi encontrado!");
    }
}