package meli.freshfood.service;

import meli.freshfood.model.Product;
import meli.freshfood.model.ProductPurchaseOrder;
import meli.freshfood.model.PurchaseOrder;

import java.math.BigDecimal;

public interface ProductPurchaseOrderService {
        ProductPurchaseOrder findById(Long id);
        ProductPurchaseOrder create(Product product, PurchaseOrder purchaseOrder, Integer quantity);
        BigDecimal totalPriceAllProducts(PurchaseOrder purchaseOrder);
}
