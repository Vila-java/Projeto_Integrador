package meli.freshfood.model;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(precision = 11, scale = 2)
    private BigDecimal price;

    private Double weight;
}
