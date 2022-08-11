package meli.freshfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
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


