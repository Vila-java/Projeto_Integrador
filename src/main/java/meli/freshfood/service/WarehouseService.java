package meli.freshfood.service;

import meli.freshfood.dto.ProductWarehouseDTO;
import meli.freshfood.dto.WarehouseDTO;
import meli.freshfood.model.Warehouse;

import java.util.List;

public interface WarehouseService {
    Warehouse findById(Long id);

    ProductWarehouseDTO findByProduct(Long productId);
}