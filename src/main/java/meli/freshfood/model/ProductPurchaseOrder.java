package meli.freshfood.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
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
}
