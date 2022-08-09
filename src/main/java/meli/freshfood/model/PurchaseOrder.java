package meli.freshfood.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(columnDefinition = "date", nullable = false)
    private LocalDate purchaseDate;

    @Enumerated(EnumType.STRING)
    private StatusPurchaseOrder statusPurchaseOrder;


    @ManyToMany
    @JoinTable(name = "product_purchaseOrder", // nome da tabela de ligacao
            joinColumns = @JoinColumn(name = "purchaseOrder_id", referencedColumnName = "id"), //atributo do author na tabela de ligacao
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "productId")) // att do book na tabela de ligacao
    @JsonIgnoreProperties("purchaseOrders")
    private Set<Product> products;


    @OneToOne
    @JoinColumn(name = "client_id")
    @JsonIgnoreProperties("purchaseOrder")
    private Client client;

}
