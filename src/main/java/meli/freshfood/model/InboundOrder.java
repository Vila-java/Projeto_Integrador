package meli.freshfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import meli.freshfood.dto.InboundOrderDTO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class InboundOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNumber;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(columnDefinition = "date", nullable = false)
    private LocalDate orderDate;

    @OneToMany(mappedBy = "inboundOrder")
    @JsonIgnore()
    private List<Batch> batch;

    @ManyToOne
    @JoinColumn(name="supervisor_id")
    @JsonIgnore()
    private Supervisor supervisor;

    @ManyToOne
    @JoinColumn(name = "section_id")
    @JsonIgnore()
    private Section section;

    public InboundOrder(InboundOrderDTO inboundOrderDTO, Supervisor supervisor, Section section) {
        orderDate = inboundOrderDTO.getOrderDate();
        this.supervisor = supervisor;
        this.section = section;
    }
}
