package meli.freshfood.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "uniqueSupervisor", columnNames = {"supervisor_id"})
})
public class Warehouse {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseId;

    @Column(nullable = false, length = 100)
    private String addressCode;

    @OneToMany(mappedBy = "warehouse")
    @JsonIgnoreProperties("warehouse")
    private List<Section> sections;

    @OneToOne
    @JoinColumn(name = "supervisor_id")
    @JsonIgnoreProperties({"warehouse", "inboundOrders"})
    private Supervisor supervisor;
}


