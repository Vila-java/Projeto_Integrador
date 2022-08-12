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
        new BatchDTO(id, productId, 23F, 10F,
                quantity, quantity, LocalDate.now(), LocalDateTime.now(), LocalDate.now());
        return null;
    }

    public static List<BatchDTO> newBatchDTOList() {
        List<BatchDTO> batchesDTO = new ArrayList<>();
        batchesDTO.add(newBatchDTOWithParams(1L, 1L, 1));
        batchesDTO.add(newBatchDTOWithParams(2L, 2L, 3));
        batchesDTO.add(newBatchDTOWithParams(3L, 3L, 4));
        return batchesDTO;
    }

}
