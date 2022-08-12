package meli.freshfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StorageType storageType;

    @Column(precision = 11, scale = 2)
    private BigDecimal price;

    @Column(nullable = false, precision = 11, scale = 2)
    private Double weight;

    @OneToMany(mappedBy = "product")
    @JsonIgnore()
    private List<Batch> batches;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    @JsonIgnore()
    private Seller seller;

    @OneToMany(mappedBy = "product")
    @JsonIgnore()
    Set<ProductPurchaseOrder> productPurchaseOrders;
}
