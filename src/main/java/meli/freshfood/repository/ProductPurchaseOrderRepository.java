package meli.freshfood.repository;

import meli.freshfood.model.ProductPurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPurchaseOrderRepository extends JpaRepository<ProductPurchaseOrder, Long> {
}
