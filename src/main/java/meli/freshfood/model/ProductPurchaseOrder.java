package meli.freshfood.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class ProductPurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore()
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JsonIgnore()
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrder purchaseOrder;

    private Integer productQuantity;

    public ProductPurchaseOrder(Product product, PurchaseOrder purchaseOrder, Integer productQuantity) {
        this.product = product;
        this.purchaseOrder = purchaseOrder;
        this.productQuantity = productQuantity;
    }
}
