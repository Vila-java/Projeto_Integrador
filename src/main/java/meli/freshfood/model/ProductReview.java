package meli.freshfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import meli.freshfood.dto.ProductReviewCreateDTO;
import net.bytebuddy.asm.Advice;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productReviewId;

    @Column(length = 200)
    private String description;

    @Column(nullable = false)
    @Max(5)
    @Min(1)
    private Integer grade;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(columnDefinition = "date", nullable = false)
    private LocalDate createdAt;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(columnDefinition = "date", nullable = false)
    private LocalDate updatedAt;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIncludeProperties(value = "clientId")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIncludeProperties(value = "productId")
    private Product product;

    public ProductReview(ProductReviewCreateDTO ProductReviewCreateDTO, Client client, Product product) {
        description = ProductReviewCreateDTO.getDescription();
        grade = ProductReviewCreateDTO.getGrade();
        createdAt = LocalDate.now();
        updatedAt = LocalDate.now();
        this.client = client;
        this.product = product;
    }
}
