package meli.freshfood.service;

import meli.freshfood.model.Warehouse;

import java.util.Optional;

public interface WarehouseService {
    Optional<Warehouse> findById(Long id);
}
