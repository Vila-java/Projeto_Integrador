package meli.freshfood.service;

import meli.freshfood.model.Warehouse;

public interface WarehouseService {
    Warehouse findById(Long id);
}