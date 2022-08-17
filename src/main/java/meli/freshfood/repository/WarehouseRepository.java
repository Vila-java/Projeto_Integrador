package meli.freshfood.repository;

import meli.freshfood.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Warehouse repository.
 */
@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
