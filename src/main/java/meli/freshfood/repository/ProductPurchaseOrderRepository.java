package meli.freshfood.repository;

import meli.freshfood.model.Product;
import meli.freshfood.model.ProductPurchaseOrder;
import meli.freshfood.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Product purchase order repository.
 */
@Repository
public interface ProductPurchaseOrderRepository extends JpaRepository<ProductPurchaseOrder, Long> {
    /**
     * Find all by purchase order list.
     *
     * @param purchaseOrder the purchase order
     * @return the list
     */
    List<ProductPurchaseOrder> findAllByPurchaseOrder(PurchaseOrder purchaseOrder);

    /**
     * Find by purchase order and product product purchase order.
     *
     * @param purchaseOrder the purchase order
     * @param product       the product
     * @return the product purchase order
     */
    ProductPurchaseOrder findByPurchaseOrderAndProduct(PurchaseOrder purchaseOrder, Product product);
}
