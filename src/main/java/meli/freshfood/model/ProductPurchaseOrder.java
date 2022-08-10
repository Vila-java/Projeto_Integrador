package meli.freshfood.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class ProductPurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrder purchaseOrder;

    private Integer productQuantity;

    public ProductPurchaseOrder(Product product, PurchaseOrder purchaseOrder, Integer productQuantity) {
        this.product = product;
        this.purchaseOrder = purchaseOrder;
        this.productQuantity = productQuantity;
    }
}
