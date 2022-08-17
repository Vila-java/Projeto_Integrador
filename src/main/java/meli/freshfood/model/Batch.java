package meli.freshfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import meli.freshfood.dto.BatchDTO;
import meli.freshfood.dto.BatchStockDTO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The type Batch.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    /**
     * Instantiates a new Batch.
     *
     * @param batchDTO     the batch dto
     * @param product      the product
     * @param section      the section
     * @param inboundOrder the inbound order
     */
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

    /**
     * Update by dto.
     *
     * @param batchDTO the batch dto
     */
    public void updateByDTO(BatchDTO batchDTO) {
        currentTemperature = batchDTO.getCurrentTemperature();
        minimumTemperature = batchDTO.getMinimumTemperature();
        initialQuantity = batchDTO.getInitialQuantity();
        currentQuantity = batchDTO.getCurrentQuantity();
        manufacturingDate = batchDTO.getManufacturingDate();
        manufacturingTime = batchDTO.getManufacturingTime();
        dueDate = batchDTO.getDueDate();
    }

    /**
     * To dto batch dto.
     *
     * @return the batch dto
     */
    public BatchDTO toDTO() {
        return new BatchDTO(this.batchNumber, product.getProductId(), this.currentTemperature,
                this.minimumTemperature, this.initialQuantity, this.currentQuantity, this.manufacturingDate,
                this.manufacturingTime, this.dueDate);
    }

    /**
     * To batch stock dto batch stock dto.
     *
     * @return the batch stock dto
     */
    public BatchStockDTO toBatchStockDTO() {
        return new BatchStockDTO(this.batchNumber, product.getProductId(), this.product.getStorageType(),
                this.getDueDate(), this.getCurrentQuantity(), this.getSection().getSectionId());
    }
}
