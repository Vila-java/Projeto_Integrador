package meli.freshfood.utils;
import meli.freshfood.model.*;
import meli.freshfood.dto.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import java.util.List;
import java.util.ArrayList;

public class BatchUtils {

    public static Batch newBatch() {
        Seller seller = SellerUtils.newSellerWithoutProducts();
        Product product = ProductUtils.newProduct();
        Section section = SectionUtils.newSection();
        Supervisor supervisor = SupervisorUtils.newSupervisor();
        InboundOrder inboundOrder = InboundOrderUtils.newInboundOrder(supervisor, section);

        return new Batch(1L, 30F, 22F,
                5, 10, LocalDate.of(2022, 01, 1),
                LocalDateTime.of(2022, Month.FEBRUARY, 1, 1, 1),
                LocalDate.of(2022, 01, 1),
                product, section, inboundOrder);
    }

    public static BatchDTO newBatchDTO() {
        new BatchDTO(1L, 1L, 23F, 10F,
                30, 10, LocalDate.now(), LocalDateTime.now(), LocalDate.now());
        return null;
    }

    public static BatchDTO newBatchDTOWithParams(Long id, Long productId, Integer quantity) {
        return new BatchDTO(id, productId, 23F, 10F,
                quantity, quantity, LocalDate.now(), LocalDateTime.now(), LocalDate.now());
    }

    public static List<BatchDTO> newBatchDTOList() {
        List<BatchDTO> batchesDTO = new ArrayList<>();
        batchesDTO.add(newBatchDTOWithParams(1L, 1L, 1));
        batchesDTO.add(newBatchDTOWithParams(2L, 2L, 3));
        batchesDTO.add(newBatchDTOWithParams(3L, 3L, 4));
        return batchesDTO;
    }

    public static Batch newBatchStock() {
        Seller seller = SellerUtils.newSellerWithoutProducts();
        Product product = ProductUtils.newProduct();
        Section section = SectionUtils.newSection();
        Supervisor supervisor = SupervisorUtils.newSupervisor();
        InboundOrder inboundOrder = InboundOrderUtils.newInboundOrder(supervisor, section);

        return new Batch(16L, 30F, 22F,
                5, 10, LocalDate.now().minusDays(30),
                LocalDateTime.of(2022, Month.FEBRUARY, 1, 1, 1),
                LocalDate.now().plusDays(2),
                product, section, inboundOrder);
    }

    public static List<Batch> newBatchStockList() {
        List<Batch> batches = new ArrayList<>();
        batches.add(newBatchStock());
        return batches;
    }
    public static List<BatchStockDTO> newBatchStockDTOList() {
        List<BatchStockDTO> batches = new ArrayList<>();
        batches.add(new BatchStockDTO(16L, 1L, StorageType.FRESH, LocalDate.now().plusDays(2), 10, 1L));
        return batches;
    }
}
