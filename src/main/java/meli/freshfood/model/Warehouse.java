package meli.freshfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

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
    @JsonIgnore()
    private List<Section> sections;

    @OneToOne
    @JoinColumn(name = "supervisor_id")
    @JsonIgnore()
    private Supervisor supervisor;
}


