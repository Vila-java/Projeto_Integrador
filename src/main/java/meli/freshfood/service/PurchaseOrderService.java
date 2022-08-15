package meli.freshfood.service;

import meli.freshfood.dto.ProductDTO;
import meli.freshfood.dto.ProductDetailsDTO;
import meli.freshfood.dto.PurchaseOrderDTO;
import meli.freshfood.model.PurchaseOrder;

import java.util.List;


public interface PurchaseOrderService {

   PurchaseOrder findbyId(Long id);
    List<ProductDetailsDTO> findAllProducts(Long purchaseOrderId);
    void closePurchaseOrder(Long id);
    void addProductToPurchase(PurchaseOrder purchaseOrder, ProductDTO productDTO);
    Double create(PurchaseOrderDTO purchaseOrderDTO);
}