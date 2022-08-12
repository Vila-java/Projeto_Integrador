package meli.freshfood.service;

import meli.freshfood.dto.ProductWarehouseDTO;
import meli.freshfood.dto.WarehouseDTO;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Product;
import meli.freshfood.model.Warehouse;
import meli.freshfood.model.Batch;
import meli.freshfood.repository.BatchRepository;
import meli.freshfood.repository.ProductRepository;
import meli.freshfood.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private BatchService batchService;

    @Override
    public Warehouse findById(Long id) {
        return warehouseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("O armazém não foi encontrado!"));
    }

    @Override
    public ProductWarehouseDTO findByProduct(Long productId) {
        Product product = productService.findById(productId);
        List<Batch> batches = batchService.findAllByProduct(product);

        Map<Long, Integer> collect = batches.stream()
                            .collect(Collectors.groupingBy(
                                    b -> b.getSection().getWarehouse().getWarehouseId(),
                                    Collectors.summingInt(b -> b.getCurrentQuantity())
                            ));

        List<WarehouseDTO> warehouseDTOS = new ArrayList();
        collect.forEach((k, v) -> warehouseDTOS.add(new WarehouseDTO(k, v)));

        return new ProductWarehouseDTO(productId, warehouseDTOS);
    }
}
