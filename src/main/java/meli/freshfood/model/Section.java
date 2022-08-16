package meli.freshfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sectionId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StorageType storageType;

    @Column(nullable = false, length = 6)
    private Long productCapacity;

    @OneToMany(mappedBy = "section")
    @JsonIgnore()
    private List<Batch> batches;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @JsonIgnore()
    private Warehouse warehouse;

    @OneToMany(mappedBy = "section")
    @JsonIgnore()
    private List<InboundOrder> inboundOrders;
}
