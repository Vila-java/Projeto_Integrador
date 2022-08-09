package meli.freshfood.service;

import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Client;
import meli.freshfood.model.PurchaseOrder;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    Client client = new Client();

    public Boolean clientExists(PurchaseOrder purchaseOrder) {
        if (client.getClientId().equals(purchaseOrder.getClient().getClientId())) {
            return true;
        } else {
            throw new NotFoundException("Cliente não cadastrado!");
        }

    }
}
