package meli.freshfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import meli.freshfood.dto.BatchDTO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter

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
    @JsonIgnore()
    private Product product;

    @ManyToOne
    @JoinColumn(name = "section_id")
    @JsonIgnore()
    private Section section;

    @ManyToOne
    @JoinColumn(name = "inbound_order_id")
    @JsonIgnore()
    private InboundOrder inboundOrder;

    public Batch(BatchDTO batchDTO, Product product, Section section, InboundOrder inboundOrder) {
        currentTemperature = batchDTO.getCurrentTemperature();
        minimumTemperature = batchDTO.getMinimumTemperature();
        initialQuantity = batchDTO.getInitialQuantity();
        currentQuantity = batchDTO.getCurrentQuantity();
        manufacturingDate = batchDTO.getManufacturingDate();
        manufacturingTime = batchDTO.getManufacturingTime();
        dueDate = batchDTO.getDueDate();

        this.product = product;
        this.section = section;
        this.inboundOrder = inboundOrder;
    }

    public void updateByDTO(BatchDTO batchDTO) {
        currentTemperature = batchDTO.getCurrentTemperature();
        minimumTemperature = batchDTO.getMinimumTemperature();
        initialQuantity = batchDTO.getInitialQuantity();
        currentQuantity = batchDTO.getCurrentQuantity();
        manufacturingDate = batchDTO.getManufacturingDate();
        manufacturingTime = batchDTO.getManufacturingTime();
        dueDate = batchDTO.getDueDate();
    }

    public BatchDTO toDTO() {
        return new BatchDTO(this.batchNumber, product.getProductId(), this.currentTemperature,
                this.minimumTemperature, this.initialQuantity, this.currentQuantity, this.manufacturingDate,
                this.manufacturingTime, this.dueDate);
    }
}
