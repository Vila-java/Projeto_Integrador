package meli.freshfood.service;

import meli.freshfood.model.Product;
import meli.freshfood.model.ProductPurchaseOrder;
import meli.freshfood.model.PurchaseOrder;

import java.math.BigDecimal;

/**
 * The interface Product purchase order service.
 */
public interface ProductPurchaseOrderService {
    /**
     * Find by id product purchase order.
     *
     * @param id the id
     * @return the product purchase order
     */
    ProductPurchaseOrder findById(Long id);

    /**
     * Create product purchase order.
     *
     * @param product       the product
     * @param purchaseOrder the purchase order
     * @param quantity      the quantity
     * @return the product purchase order
     */
    ProductPurchaseOrder create(Product product, PurchaseOrder purchaseOrder, Integer quantity);

    /**
     * Total price all products big decimal.
     *
     * @param purchaseOrder the purchase order
     * @return the big decimal
     */
    BigDecimal totalPriceAllProducts(PurchaseOrder purchaseOrder);

    /**
     * Find by purchase order and product product purchase order.
     *
     * @param purchaseOrder the purchase order
     * @param product       the product
     * @return the product purchase order
     */
    ProductPurchaseOrder findByPurchaseOrderAndProduct(PurchaseOrder purchaseOrder, Product product);

    /**
     * Update product purchase order.
     *
     * @param productPurchaseOrder the product purchase order
     * @param quantity             the quantity
     * @return the product purchase order
     */
    ProductPurchaseOrder update(ProductPurchaseOrder productPurchaseOrder, Integer quantity);

    /**
     * Remove all orders.
     *
     * @param purchaseOrder the purchase order
     */
    void removeAllOrders(PurchaseOrder purchaseOrder);
}
