package meli.freshfood.service;

import meli.freshfood.dto.ClientOrderDTO;
import meli.freshfood.model.ClientOrder;
import meli.freshfood.model.PurchaseOrder;

import java.util.List;

public interface ClientOrderService {
    ClientOrder findById(Long id);
    ClientOrder saveOrder(PurchaseOrder purchaseOrder);
    List<ClientOrderDTO> getAllOrders(Long clientId);
    String changeOrderStatus (Long id, String status);
}
