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
public class ProductClientOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @ManyToOne
    @JsonIgnore()
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JsonIgnore()
    @JoinColumn(name = "client_order_id")
    private ClientOrder clientOrder;

    public ProductClientOrder(Integer quantity, Product product, ClientOrder clientorder) {
        this.quantity = quantity;
        this.product = product;
        this.clientOrder = clientorder;
    }
}
