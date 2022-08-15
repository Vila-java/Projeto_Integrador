package meli.freshfood.service;


import meli.freshfood.dto.PurchaseDeliveryDTO;
import meli.freshfood.model.PurchaseDelivery;

public interface PurchaseDeliveryService {

     PurchaseDelivery save(PurchaseDeliveryDTO purchaseDelivery);
}
