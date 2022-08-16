package meli.freshfood.utils;

import meli.freshfood.dto.PurchaseDeliveryDTO;
import meli.freshfood.model.*;


public class PurchaseDeliveryUtils {

    static Carrier carrier = CarrierUtils.newCarrier();
    static PurchaseOrder purchaseOrder = PurchaseOrderUtils.newPurchaseOrderUtils();

    public static PurchaseDelivery newPurchaseDelivery() {
        return new PurchaseDelivery(1L, purchaseOrder, carrier, 2);
    }

    public static PurchaseDeliveryDTO newPurchaseDeliveryDTO() {
        return new PurchaseDeliveryDTO(1L, 1L, 1L, 2);
    }
}

