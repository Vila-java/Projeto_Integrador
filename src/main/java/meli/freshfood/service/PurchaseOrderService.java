package meli.freshfood.service;

import meli.freshfood.dto.ProductDTO;
import meli.freshfood.dto.PurchaseOrderDTO;
import meli.freshfood.model.Product;
import meli.freshfood.model.PurchaseOrder;

import java.util.List;


public interface PurchaseOrderService {

    List<Product> findAllProducts(Long purchaseOrderId);
    void closePurchaseOrder(Long id);
    void addProductToPurchase(PurchaseOrder purchaseOrder, ProductDTO productDTO);
    Double create(PurchaseOrderDTO purchaseOrderDTO);
}