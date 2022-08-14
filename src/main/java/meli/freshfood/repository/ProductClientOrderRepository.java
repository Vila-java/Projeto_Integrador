package meli.freshfood.repository;

import meli.freshfood.model.ProductClientOrder;
import meli.freshfood.model.ProductPurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductClientOrderRepository extends JpaRepository<ProductClientOrder, Long> {
}
