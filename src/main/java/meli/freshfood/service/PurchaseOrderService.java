package meli.freshfood.service;

import meli.freshfood.dto.PurchaseOrderDTO;
import meli.freshfood.model.PurchaseOrder;

import java.util.List;


public interface PurchaseOrderService {

    List<PurchaseOrder> findAllPurchaseOrder();
    PurchaseOrder statusPurchase(PurchaseOrderDTO purchaseOrderDTO, Long id);
    Double createPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO);
}