package meli.freshfood.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Supervisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supersivorId;

    @Column(nullable = false, length = 10)
    private String firstName;

    @Column(nullable = false, length = 60)
    private String lastName;

    @OneToOne(mappedBy = "supervisor")
    @JsonIgnoreProperties("supervisor")
    private Warehouse warehouse;

    @OneToMany(mappedBy = "supervisor")
    @JsonIgnoreProperties("supervisor")
    private List<InboundOrder> inboundOrders;
}
