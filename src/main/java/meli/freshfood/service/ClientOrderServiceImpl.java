package meli.freshfood.service;

import meli.freshfood.dto.ClientOrderDTO;
import meli.freshfood.model.*;
import meli.freshfood.repository.ClientOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientOrderServiceImpl implements ClientOrderService{

    @Autowired
    ClientOrderRepository clientOrderRepository;

    @Autowired
    ProductClientService productClientService;

    @Autowired
    ClientService clientService;

    @Override
    public ClientOrder saveOrder(PurchaseOrder purchaseOrder){
        ClientOrder clientOrder = new ClientOrder(null, LocalDate.now(), purchaseOrder.getClient(), StatusOrder.PENDING);
        clientOrder = clientOrderRepository.save(clientOrder);
        Set<ProductClientOrder> productClientOrder = purchaseOrder.toProductClientOrder(clientOrder);
        productClientOrder.forEach(p -> productClientService.create(p));

        return clientOrder;
    }
    @Override
    public List<ClientOrderDTO> getAllOrders (Long clientId){
        Client client = new Client();
        client.setClientId(clientId);
        List<ClientOrderDTO> clientDTO = clientOrderRepository.findByClient(client)
                .stream()
                .map(clientOrder -> clientOrder.toDTO())
                .collect(Collectors.toList());
        return clientDTO;

    }
}
