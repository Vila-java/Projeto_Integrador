package meli.freshfood.service;

import meli.freshfood.dto.ProductWarehouseDTO;
import meli.freshfood.model.Warehouse;

/**
 * The interface Warehouse service.
 */
public interface WarehouseService {
    /**
     * Find by id warehouse.
     *
     * @param id the id
     * @return the warehouse
     */
    Warehouse findById(Long id);

    /**
     * Find by product product warehouse dto.
     *
     * @param productId the product id
     * @return the product warehouse dto
     */
    ProductWarehouseDTO findByProduct(Long productId);
}