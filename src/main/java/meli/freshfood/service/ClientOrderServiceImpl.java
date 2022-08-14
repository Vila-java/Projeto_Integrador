package meli.freshfood.service;

import meli.freshfood.dto.ClientOrderDTO;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.*;
import meli.freshfood.repository.ClientOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
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
    public ClientOrder findById(Long id) {
        return clientOrderRepository.findById(id).orElseThrow(() -> new NotFoundException("Pedido não encontrado!"));
    }
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

    @Override
    public String changeOrderStatus (Long id, String status) {
        ClientOrder clientOrder = this.findById(id);
        try{
            StatusOrder.valueOf(status);
        }
        catch (IllegalArgumentException ex){
            throw new IllegalArgumentException("Entrada invalida! Possiveis valores: " + Arrays.toString(StatusOrder.values()));
        }

        clientOrder.setOrderStatus(StatusOrder.valueOf(status));
        clientOrderRepository.save(clientOrder);
        return "Status do pedido foi alterado com sucesso!";

    }
}
