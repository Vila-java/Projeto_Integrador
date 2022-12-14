package meli.freshfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import meli.freshfood.dto.PurchaseOrderDTO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * The type Purchase order.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(columnDefinition = "date", nullable = false)
    private LocalDate purchaseDate;

    @Enumerated(EnumType.STRING)
    private StatusPurchaseOrder orderStatus;

    /**
     * The Product purchase orders.
     */
    @OneToMany(mappedBy = "purchaseOrder")
    @JsonIgnore()
    Set<ProductPurchaseOrder> productPurchaseOrders;

    @OneToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore()
    private Client client;

    /**
     * Instantiates a new Purchase order.
     *
     * @param purchaseOrderDTO the purchase order dto
     * @param client           the client
     */
    public PurchaseOrder(PurchaseOrderDTO purchaseOrderDTO, Client client) {
        orderStatus = StatusPurchaseOrder.valueOf(purchaseOrderDTO.getOrderStatus());
        purchaseDate = purchaseOrderDTO.getDate();
        this.client = client;
    }
}
