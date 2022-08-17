package meli.freshfood.service;

import meli.freshfood.dto.BatchStockDTO;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.*;
import meli.freshfood.repository.BatchRepository;
import meli.freshfood.utils.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BatchServiceImplTest {

    @InjectMocks
    BatchServiceImpl batchService;

    @Mock
    BatchRepository batchRepo;

    @Mock
    ProductService productService;

    @Test
    @DisplayName("Retorna o estoque quando existir")
    void findById_returnBatch_whenBatchIdExists() {
        Optional<Batch> batchMocked = Optional.of(BatchUtils.newBatch());

        BDDMockito.when(batchRepo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(batchMocked);

        Batch batch = batchService.findById(ArgumentMatchers.anyLong());

        assertThat(batch.getBatchNumber()).isPositive();
        assertThat(batch.getClass()).isEqualTo(Batch.class);
    }

    @Test
    @DisplayName("Retorna exceção caso o Id não exista")
    void findById_ShouldReturnNotFoundException_whenBatchIdNotExists() {
        BDDMockito.when(batchRepo.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new NotFoundException("O lote não foi encontrado!"));

        Exception exception = assertThrows(
                NotFoundException.class,
                () -> batchService.findById(ArgumentMatchers.anyLong())
        );

        assertThat(exception.getMessage()).isEqualTo("O lote não foi encontrado!");
    }

    @Test
    void findAllByProduct() {
        BDDMockito.when(batchRepo.findAllByProduct(Mockito.any(Product.class)))
                .thenReturn(BatchUtils.newBatchesList());

        List<Batch> batches = batchService.findAllByProduct(ProductUtils.newProduct());

        List<Batch> expected = BatchUtils.newBatchesList();

        assertThat(batches.get(0).getBatchNumber()).isEqualTo(expected.get(0).getBatchNumber());
    }

    @Test
    void totalAvailableBatchesCapacity_ShouldReturnTotalCapacity()
    {
        Integer capacity = batchService.totalAvailableBatchesCapacity(BatchUtils.newBatchesList());
        Integer expected = 0;
        assertThat(capacity).isEqualTo(expected);
    }

    @Test
    void checkBatchAvailable_ShouldReturnTrue_IfExistsTheAmountOfProductsRequested()
    {
        BDDMockito.when(productService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(ProductUtils.newProductWithBatches());
        Boolean batchAvailable = batchService.checkBatchAvailable(ProductUtils.newProductDTO());

        assertThat(batchAvailable).isEqualTo(true);
    }
    @Test
    public void findBatchesFilteredByDueDateIntervalAndSection_ShouldReturnBatchesFiltered() {

        BDDMockito.when(batchRepo.findAll()).thenReturn(BatchUtils.newBatchStockList());
        Integer intervalDate = 5;
        Long sectionId = 1L;

        List<BatchStockDTO> batchesFilteredByDueDateAndSection = batchService.findBatches(intervalDate, sectionId, null, true);

        List<BatchStockDTO> expected = BatchUtils.newBatchStockDTOList();

        assertThat(batchesFilteredByDueDateAndSection.get(0).getBatchNumber()).isEqualTo(expected.get(0).getBatchNumber());
        assertThat(batchesFilteredByDueDateAndSection.get(0).getSectionId()).isEqualTo(expected.get(0).getSectionId());

    }

    @Test
    public void findBatchesFilteredByDueDateIntervalAndCategory_ShouldReturnBatchesFiltered() {
        BDDMockito.when(batchRepo.findAll()).thenReturn(BatchUtils.newBatchStockList());
        Integer intervalDate = 5;

        List<BatchStockDTO> batchesFilteredByDueDateAndSection = batchService.findBatches(intervalDate, null, "FR", true);

        List<BatchStockDTO> expected = BatchUtils.newBatchStockDTOList();

        assertThat(batchesFilteredByDueDateAndSection.get(0).getBatchNumber()).isEqualTo(expected.get(0).getBatchNumber());
        assertThat(batchesFilteredByDueDateAndSection.get(0).getProductTypeId()).isEqualTo(expected.get(0).getProductTypeId());

    }

    @DisplayName("Retorna todos os lotes do produto passado")
    @Test
    void findAllByProduct_returnBatches_whenProductPassed() {
        List<Batch> batchesMocked = BatchUtils.newBatchesList();

        BDDMockito.when(batchRepo.findAllByProduct(ArgumentMatchers.any(Product.class)))
                .thenReturn(batchesMocked);

        Product product = ProductUtils.newProduct();
        List<Batch> batches = batchService.findAllByProduct(product);

        assertThat(batches.size()).isEqualTo(batchesMocked.size());
        assertThat(batches.getClass()).isEqualTo(ArrayList.class);
    }

    @Test
    @DisplayName("Retorna o lote salvo")
    void save_returnBatch() {
        Batch batchToSave = BatchUtils.newBatch();

        BDDMockito.when(batchRepo.save(ArgumentMatchers.any(Batch.class)))
                .thenReturn(batchToSave);

        Batch batch = batchService.save(batchToSave);

        assertThat(batch.getBatchNumber()).isPositive();
        assertThat(batch.getClass()).isEqualTo(Batch.class);
    }

    @Test
    @DisplayName("Retorna todos os lotes que não vão vencer nas próximas 3 semanas")
    void filterNotExpiredProducts_returnValidBatches_whenBatchesNotExpired() {
        Integer weeksToDue = 4;
        LocalDate dueDate = LocalDate.now().plusWeeks(weeksToDue);
        List<Batch> batchesMocked = BatchUtils.newBatchesListWithDueDate(dueDate);

        List<Batch> batches = batchService.filterNotExpiredProducts(batchesMocked);

        assertThat(batches.size()).isEqualTo(batchesMocked.size());
        assertThat(batches.getClass()).isEqualTo(ArrayList.class);
    }

    @Test
    @DisplayName("Retorna lista vazia quando todos os lotes passados estão vencidos")
    void filterNotExpiredProducts_returnEmptyList_whenAllBatchesExpired() {
        Integer weeksToDue = 0;
        LocalDate dueDate = LocalDate.now().plusWeeks(weeksToDue);
        List<Batch> batchesMocked = BatchUtils.newBatchesListWithDueDate(dueDate);

        List<Batch> batches = batchService.filterNotExpiredProducts(batchesMocked);

        assertThat(batches.size()).isZero();
        assertThat(batches.getClass()).isEqualTo(ArrayList.class);
    }
}
