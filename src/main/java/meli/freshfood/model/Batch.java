package meli.freshfood.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchNumber;

    @Column(nullable = false, length = 3)
    private Float currentTemperature;

    @Column(nullable = false, length = 3)
    private Float minimumTemperature;

    @Column(nullable = false, length = 7)
    private Integer initialQuantity;

    @Column(nullable = false, length = 7)
    private Integer currentQuantity;


    //TODO: Verificar a utilizacao do manufacturingDate e manufacturingTime;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(columnDefinition = "date", nullable = false)
    private LocalDate manufacturingDate;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(columnDefinition = "date", nullable = false)
    private LocalDateTime manufacturingTime;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(columnDefinition = "date", nullable = false)
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("batches")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "section_id")
    @JsonIgnoreProperties("batches")
    private Section section;

    @ManyToOne
    @JoinColumn(name = "inbound_order_id")
    @JsonIgnoreProperties("batch")
    private InboundOrder inboundOrder;
}
