package meli.freshfood.service;

import meli.freshfood.dto.BatchDetailsDTO;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.*;
import meli.freshfood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("O produto não foi encontrado!"));
    }

    @Override
    public List<Product> findAll() {
        List<Product> listProducts = productRepository.findAll();

        if(listProducts.isEmpty())  {
            throw new NotFoundException("A lista de produtos não foi encontrada!");
        }
        return listProducts;
    }

    @Override
    public List<Product> findProductByCategory(String storageType) {
        StorageType storageTypeObj = StorageType.parseToStorage(storageType);
        List<Product> listProducts = productRepository.findByStorageType(storageTypeObj);
        if(listProducts.isEmpty())  {
            throw new NotFoundException("A lista de produtos não foi encontrada!");
        }
        return listProducts;
    }

    // TODO: Verificar uma forma melhor de fazer, colocar em outra classe
    public List<BatchDetailsDTO> sortByCurrentQuantity(List<BatchDetailsDTO> batches) {
        return batches.stream()
                .sorted((b1, b2) -> b1.getCurrentQuantity() > b2.getCurrentQuantity() ? 1 : 0)
                .collect(Collectors.toList());
    }

    public List<BatchDetailsDTO> sortByDueDate(List<BatchDetailsDTO> batches) {
        return batches.stream()
                .sorted((b1, b2) -> b1.getDueDate().isAfter(b2.getDueDate()) ? 1 : 0)
                .collect(Collectors.toList());
    }

    public List<BatchDetailsDTO> sortByBatchNumber(List<BatchDetailsDTO> batches) {
        return batches.stream()
                .sorted((b1, b2) -> b1.getBatchNumber().compareTo(b2.getBatchNumber()) > 0 ? 1 : 0)
                .collect(Collectors.toList());
    }

    public List<BatchDetailsDTO> sort(List<BatchDetailsDTO> batches, String batchOrder) {
        if(batchOrder.equalsIgnoreCase("Q")) {
            return sortByCurrentQuantity(batches);
        } else if(batchOrder.equalsIgnoreCase("V")) {
            return sortByDueDate(batches);
        } else {
            return sortByBatchNumber(batches);
        }
    }

    @Override
    public List<BatchDetailsDTO> getBatches(Long productId, String batchOrder) {
        Product product = findById(productId);

        List<BatchDetailsDTO> batches = product.getBatches().stream()
                .map((batch) -> {
                    Section section = batch.getSection();
                    Warehouse warehouse = section.getWarehouse();
                    return new BatchDetailsDTO(
                            batch.getBatchNumber(), batch.getCurrentTemperature(),
                            batch.getCurrentQuantity(), batch.getDueDate(),
                            warehouse.getWarehouseId(), section.getSectionId()
                    );
                }).collect(Collectors.toList());

        if(batchOrder != null) {
            batches = sort(batches, batchOrder);
        }

        return batches;
    }
}