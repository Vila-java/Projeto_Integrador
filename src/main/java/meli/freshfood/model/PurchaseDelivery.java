package meli.freshfood.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class PurchaseDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private PurchaseOrder purchaseOrder;

    @OneToOne
    @JoinColumn(name = "id")
    private Carrier carrier;

    private Integer quantityOfBox;

}
