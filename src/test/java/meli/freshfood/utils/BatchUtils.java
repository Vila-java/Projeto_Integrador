package meli.freshfood.utils;
import meli.freshfood.model.*;
import meli.freshfood.dto.*;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class BatchUtils {

    public static Batch newBatch() {
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

    public static Batch newBatchWithProduct(Product product) {
        Section section = SectionUtils.newSection();
        Supervisor supervisor = SupervisorUtils.newSupervisor();
        InboundOrder inboundOrder = InboundOrderUtils.newInboundOrder(supervisor, section);

        return new Batch(1L, 30F, 22F,
                5, 10, LocalDate.of(2022, 01, 1),
                LocalDateTime.of(2022, Month.FEBRUARY, 1, 1, 1),
                LocalDate.of(2022, 01, 1),
                product, section, inboundOrder);
    }

    public static Batch newBatchWithDueDate(LocalDate dueDate) {
        Section section = SectionUtils.newSection();
        Supervisor supervisor = SupervisorUtils.newSupervisor();
        InboundOrder inboundOrder = InboundOrderUtils.newInboundOrder(supervisor, section);
        Product product = ProductUtils.newProduct();

        return new Batch(1L, 30F, 22F,
                5, 10, LocalDate.of(2022, 01, 1),
                LocalDateTime.of(2022, Month.FEBRUARY, 1, 1, 1),
                dueDate, product, section, inboundOrder);
    }

    public static List<Batch> newBatchesList() {
        Product product = ProductUtils.newProduct();
        List<Batch> batches = new ArrayList<>();
        batches.add(newBatchWithProduct(product));
        batches.add(newBatchWithProduct(product));
        batches.add(newBatchWithProduct(product));
        return batches;
    }

    public static List<Batch> newBatchesListWithDueDate(LocalDate dueDate) {
        List<Batch> batches = new ArrayList<>();
        batches.add(newBatchWithDueDate(dueDate));
        batches.add(newBatchWithDueDate(dueDate));
        batches.add(newBatchWithDueDate(dueDate));
        return batches;
    }

    public static List<Batch> newBatchesListValidDueDate() {
        Product product = ProductUtils.newProduct();
        List<Batch> batches = new ArrayList<>();
        batches.add(newBatchWithProduct(product));
        batches.add(newBatchWithProduct(product));
        batches.add(newBatchWithProduct(product));
        return batches;
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
}
