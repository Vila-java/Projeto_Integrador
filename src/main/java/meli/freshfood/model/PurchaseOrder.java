package meli.freshfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import meli.freshfood.dto.PurchaseOrderDTO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter

@Entity
@NoArgsConstructor
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(columnDefinition = "date", nullable = false)
    private LocalDate purchaseDate;

    @Enumerated(EnumType.STRING)
    private StatusPurchaseOrder orderStatus;

    @OneToMany(mappedBy = "purchaseOrder")
    @JsonIgnore()
    Set<ProductPurchaseOrder> productPurchaseOrders;

    @OneToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore()
    private Client client;

    public PurchaseOrder(PurchaseOrderDTO purchaseOrderDTO, Client client) {
        orderStatus = StatusPurchaseOrder.valueOf(purchaseOrderDTO.getOrderStatus());
        purchaseDate = purchaseOrderDTO.getDate();
        this.client = client;
    }
}
