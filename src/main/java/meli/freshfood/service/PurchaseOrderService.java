package meli.freshfood.service;

import meli.freshfood.dto.ProductDTO;
import meli.freshfood.dto.ProductDetailsDTO;
import meli.freshfood.dto.PurchaseOrderDTO;
import meli.freshfood.model.PurchaseOrder;

import java.util.List;


/**
 * The interface Purchase order service.
 */
public interface PurchaseOrderService {

    /**
     * Find all products list.
     *
     * @param purchaseOrderId the purchase order id
     * @return the list
     */
    List<ProductDetailsDTO> findAllProducts(Long purchaseOrderId);

    /**
     * Close purchase order.
     *
     * @param id the id
     */
    void closePurchaseOrder(Long id);

    /**
     * Add product to purchase.
     *
     * @param purchaseOrder the purchase order
     * @param productDTO    the product dto
     */
    void addProductToPurchase(PurchaseOrder purchaseOrder, ProductDTO productDTO);

    /**
     * Create double.
     *
     * @param purchaseOrderDTO the purchase order dto
     * @return the double
     */
    Double create(PurchaseOrderDTO purchaseOrderDTO);
}