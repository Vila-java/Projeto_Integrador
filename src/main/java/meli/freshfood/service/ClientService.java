package meli.freshfood.service;

import meli.freshfood.model.Client;
import meli.freshfood.model.PurchaseOrder;

import java.util.List;

public interface ClientService {

    Client findById(Long id);
    List<PurchaseOrder> findAllPurchaseOrder(Long id);
    List<Client> findAllClient();
    Client save(Client client);
    Client update(Client client);
    void delete(Long clientId);
}
