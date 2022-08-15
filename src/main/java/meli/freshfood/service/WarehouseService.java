package meli.freshfood.service;

import meli.freshfood.dto.ProductWarehouseDTO;
import meli.freshfood.model.Warehouse;

public interface WarehouseService {
    Warehouse findById(Long id);

    ProductWarehouseDTO findByProduct(Long productId);
}