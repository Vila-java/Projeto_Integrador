package meli.freshfood.service;

import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.*;
import meli.freshfood.repository.BatchRepository;
import meli.freshfood.utils.*;
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
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BatchServiceImplTest {

    @InjectMocks
    BatchServiceImpl batchService;

    @Mock
    BatchRepository batchRepo;

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
    void returnNotFoundException_whenBatchIdNotExists() {
        BDDMockito.when(batchRepo.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new NotFoundException("O lote não foi encontrado!"));

        Exception exception = assertThrows(
                NotFoundException.class,
                () -> batchService.findById(ArgumentMatchers.anyLong())
        );

        assertThat(exception.getMessage()).isEqualTo("O lote não foi encontrado!");
    }
}
