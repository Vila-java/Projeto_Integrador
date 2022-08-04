package meli.freshfood.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
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
}
