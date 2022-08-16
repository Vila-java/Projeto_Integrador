package meli.freshfood.controller;

import meli.freshfood.dto.BatchDetailsDTO;
import meli.freshfood.dto.BatchStockDTO;
import meli.freshfood.dto.ProductBatchesDTO;
import meli.freshfood.dto.ProductPromotionDTO;
import meli.freshfood.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class BatchController {

    @Autowired
    private BatchService batchService;

    @GetMapping("/due-date")
    public ResponseEntity<List<BatchStockDTO>> findBatchesFilteredByDueDateAndSection
            (@RequestParam Integer intervalDate, @RequestParam(required = false) Long sectionId) {
        return ResponseEntity.ok(batchService.findBatches(intervalDate, sectionId, null, true));
    }

    @GetMapping("/list/batch")
    public ResponseEntity<ProductBatchesDTO> findProductByCategory(
            @RequestParam Long productId,
            @RequestParam(required = false) String batchOrder
    ) {
        List<BatchDetailsDTO> batchDetailsDTOS = batchService.getBatchesByProduct(productId, batchOrder);

        ProductBatchesDTO productBatchesDTO = new ProductBatchesDTO(productId, batchDetailsDTOS);
        return ResponseEntity.ok(productBatchesDTO);
    }

    @GetMapping("/due-date/list")
    public ResponseEntity<List<BatchStockDTO>> findBatchesByCategoryAndDueDate(
            @RequestParam Integer intervalDate,
            @RequestParam(required = false) String storageType,
            @RequestParam(required = false) Boolean order) {

        List<BatchStockDTO> batch = batchService.findBatches(intervalDate,null,  storageType, order);
        return ResponseEntity.ok(batch);
    }

    @GetMapping("due-date/products-promotion30")
    public ResponseEntity<List<ProductPromotionDTO>> findBatchesFilteredByPromotion30() {
        List<ProductPromotionDTO> productPromotion = batchService.findBatchesFilteredByPromotion30();
        return ResponseEntity.ok(productPromotion);
    }

    @GetMapping("due-date/products-promotion60")
    public ResponseEntity<List<ProductPromotionDTO>> findBatchesFilteredByPromotion60() {
        List<ProductPromotionDTO> productPromotion = batchService.findBatchesFilteredByPromotion60();
        return ResponseEntity.ok(productPromotion);
    }

    @GetMapping("due-date/products-trash")
    public ResponseEntity<List<ProductPromotionDTO>> findBatchesFilteredByProductsTrash() {
        List<ProductPromotionDTO> productPromotion = batchService.findBatchesFilteredByProductsTrash();
        return ResponseEntity.ok(productPromotion);
    }
}
