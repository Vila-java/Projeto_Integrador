package meli.freshfood.service;

import meli.freshfood.dto.WarehouseDTO;
import meli.freshfood.model.Warehouse;

import java.util.List;

public interface WarehouseService {
    Warehouse findById(Long id);
    List<WarehouseDTO> findByProduct(Long id);
}