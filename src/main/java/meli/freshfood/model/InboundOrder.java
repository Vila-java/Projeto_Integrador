package meli.freshfood.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import meli.freshfood.dto.InboundOrderDTO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class InboundOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNumber;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(columnDefinition = "date", nullable = false)
    private LocalDate orderDate;

    @OneToMany(mappedBy = "inboundOrder")
    @JsonIgnoreProperties("inboundOrder")
    private List<Batch> batch;

    @ManyToOne
    @JoinColumn(name="supervisor_id")
    @JsonIgnoreProperties({"inboundOrders", "warehouse"})
    private Supervisor supervisor;

    @ManyToOne
    @JoinColumn(name = "section_id")
    @JsonIgnoreProperties({"inboundOrders", "warehouse"})
    private Section section;

    public InboundOrder(InboundOrderDTO inboundOrderDTO, Supervisor supervisor, Section section) {
        orderDate = inboundOrderDTO.getOrderDate();
        this.supervisor = supervisor;
        this.section = section;
    }
}
