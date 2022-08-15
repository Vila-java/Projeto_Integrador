package meli.freshfood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDeliveryDTO {

    private Long vehicleId;
    private Long purchaseOrderId;
    private Long carrierId;
    private Integer quantityOfBox;
}
