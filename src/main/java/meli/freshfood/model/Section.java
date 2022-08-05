package meli.freshfood.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.session.StoreType;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sectionId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreType storageType;

    @Column(nullable = false, length = 6)
    private Long productCapacity;

    @OneToMany(mappedBy = "section")
    @JsonIgnoreProperties("section")
    private List<Batch> batches;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @JsonIgnoreProperties("sections")
    private Warehouse warehouse;

    @OneToMany(mappedBy = "section")
    @JsonIgnoreProperties("section")
    private List<InboundOrder> inboundOrders;
}
