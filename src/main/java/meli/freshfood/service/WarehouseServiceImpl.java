package meli.freshfood.service;


import meli.freshfood.dto.WarehouseDTO;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Warehouse;
import meli.freshfood.repository.BatchRepository;
import meli.freshfood.repository.ProductRepository;
import meli.freshfood.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BatchRepository batchRepository;


    @Override
    public Warehouse findById(Long id) {
        return warehouseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("O armazém não foi encontrado!"));
    }

    @Override
    public List<WarehouseDTO> findByProduct(Long productId) {
//        List<Batch> listProductsBatches = batchRepository.findAllByProduct(productRepository.findById(productId)
//                .orElseThrow(() -> new NotFoundException("O produto não foi encontrado!")));
//        listProductsBatches.stream()
//            .collect(Collectors.groupingBy(p ->p.getSection().getWarehouse().getWarehouseId(),
//                    Collectors.summingInt(Batch::getCurrentQuantity)))
//                    .forEach();
//
//        listProductsBatches.stream()
//                .collect(Collectors.groupingBy(batch -> batch.getSection().getWarehouse().getWarehouseId()))
//                .entrySet().stream()
//                .map(e -> e.getValue().stream()
//                        .reduce((f1,f2) -> new WarehouseDTO(f1.getSection().getWarehouse().getWarehouseId(),
//                                f1.getCurrentQuantity() + f2.getCurrentQuantity())))
//                .map(f -> f.get())
//                .collect(Collectors.toList());
//
//
        return null;
    }

}