package meli.freshfood.repository;

import meli.freshfood.model.Product;
import meli.freshfood.model.ProductPurchaseOrder;
import meli.freshfood.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPurchaseOrderRepository extends JpaRepository<ProductPurchaseOrder, Long> {
    List<ProductPurchaseOrder> findAllByPurchaseOrder(PurchaseOrder purchaseOrder);
    ProductPurchaseOrder findByPurchaseOrderAndProduct(PurchaseOrder purchaseOrder, Product product);
}
