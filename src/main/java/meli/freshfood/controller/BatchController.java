package meli.freshfood.controller;

import meli.freshfood.dto.BatchStockDTO;
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
    public ResponseEntity<List<BatchStockDTO>> getProductsFiltered (@RequestParam Integer intervalDate) {
        return ResponseEntity.ok(batchService.getByDueDate(intervalDate));
    }
}
