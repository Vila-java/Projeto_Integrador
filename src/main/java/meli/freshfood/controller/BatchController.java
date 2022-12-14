package meli.freshfood.controller;

import meli.freshfood.dto.BatchDetailsDTO;
import meli.freshfood.dto.BatchStockDTO;
import meli.freshfood.dto.ProductBatchesDTO;
import meli.freshfood.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Batch controller.
 */
@RestController
@RequestMapping("/api/v1/fresh-products")
public class BatchController {

    @Autowired
    private BatchService batchService;

    /**
     * Find batches filtered by due date and section response entity.
     *
     * @param intervalDate the interval date
     * @param sectionId    the section id
     * @return the response entity
     */
    @GetMapping("/due-date")
    public ResponseEntity<List<BatchStockDTO>> findBatchesFilteredByDueDateAndSection
            (@RequestParam Integer intervalDate,
             @RequestParam(required = false) Long sectionId) {
        return ResponseEntity.ok(batchService.findBatches(intervalDate, sectionId, null, true));
    }

    /**
     * Find product by category response entity.
     *
     * @param productId  the product id
     * @param batchOrder the batch order
     * @return the response entity
     */
    @GetMapping("/list/batch")
    public ResponseEntity<ProductBatchesDTO> findProductByCategory(
            @RequestParam Long productId,
            @RequestParam(required = false) String batchOrder
    ) {
        List<BatchDetailsDTO> batchDetailsDTOS = batchService.getBatchesByProduct(productId, batchOrder);

        ProductBatchesDTO productBatchesDTO = new ProductBatchesDTO(productId, batchDetailsDTOS);
        return ResponseEntity.ok(productBatchesDTO);
    }

    /**
     * Find batches by category and due date response entity.
     *
     * @param intervalDate the interval date
     * @param storageType  the storage type
     * @param order        the order
     * @return the response entity
     */
    @GetMapping("/due-date/list")
    public ResponseEntity<List<BatchStockDTO>> findBatchesByCategoryAndDueDate(
            @RequestParam Integer intervalDate,
            @RequestParam(required = false) String storageType,
            @RequestParam(required = false) Boolean order) {

        List<BatchStockDTO> batch = batchService.findBatches(intervalDate,null,  storageType, order);
        return ResponseEntity.ok(batch);
    }
}
