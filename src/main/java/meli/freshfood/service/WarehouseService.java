package meli.freshfood.service;

public interface WarehouseService {
    boolean isValid(Long warehouseId);
    boolean supervisorBelongsToWarehouse (Long supervisorId);
}
