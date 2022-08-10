package meli.freshfood.service;

import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Product;
import meli.freshfood.model.ProductPurchaseOrder;
import meli.freshfood.model.PurchaseOrder;
import meli.freshfood.repository.ProductPurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductPurchaseOrderServiceImpl implements ProductPurchaseOrderService {
    @Autowired
    private ProductPurchaseOrderRepository productPurchaseOrderRepository;

    public ProductPurchaseOrder create(Product product, PurchaseOrder purchaseOrder, Integer quantity) {
        ProductPurchaseOrder productPurchaseOrder = new ProductPurchaseOrder(product, purchaseOrder, quantity);
        return productPurchaseOrderRepository.save(productPurchaseOrder);
    }

    @Override
    public BigDecimal totalPriceAllProducts(PurchaseOrder purchaseOrder) {
        BigDecimal totalPrice = purchaseOrder.getProductPurchaseOrders().stream().map((po) -> {
            return po.getProduct().getPrice().multiply(new BigDecimal(po.getProductQuantity()));
        }).reduce(new BigDecimal(0), (po1, po2) -> po1.add(po2));
        return totalPrice;
    }

    @Override
    public ProductPurchaseOrder findById(Long id){
        return productPurchaseOrderRepository.findById(id)
                .orElseThrow(()->
                   new NotFoundException("Não existe essa relação!"));
    }

}
