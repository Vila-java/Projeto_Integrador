package meli.freshfood.service;

import meli.freshfood.model.PurchaseOrder;

public interface ClientService {
    Boolean clientExists (PurchaseOrder purchaseOrder);
}
