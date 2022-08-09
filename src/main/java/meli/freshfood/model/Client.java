package meli.freshfood.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @Column(nullable = false, length = 10)
    private String firstName;

    @Column(nullable = false, length = 60)
    private String lastName;

    @OneToOne(mappedBy = "client")
    @JsonIgnoreProperties("client")
    private PurchaseOrder purchaseOrder;
}
