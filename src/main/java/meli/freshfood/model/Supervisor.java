package meli.freshfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Supervisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supersivorId;

    @Column(nullable = false, length = 10)
    private String firstName;

    @Column(nullable = false, length = 60)
    private String lastName;

    @OneToOne(mappedBy = "supervisor")
    @JsonIgnore()
    private Warehouse warehouse;

    @OneToMany(mappedBy = "supervisor")
    @JsonIgnore()
    private List<InboundOrder> inboundOrders;
}
