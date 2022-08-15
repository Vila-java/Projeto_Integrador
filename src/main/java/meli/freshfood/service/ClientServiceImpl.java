package meli.freshfood.service;

import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Client;
import meli.freshfood.model.PurchaseOrder;
import meli.freshfood.repository.ClientRepository;
import meli.freshfood.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client findById(Long id) {
       return clientRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("O cliente não foi encontrado!"));
    }

    @Override
    public List<Client> findAllClient() {
        return clientRepository.findAll();
    }

    public List<PurchaseOrder> findAllPurchaseOrder(Long id){
        return clientRepository.findById(id).get().getPurchaseOrder();
    }
    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client update(Client client) {
        findById(client.getClientId());
        return clientRepository.save(client);
    }

    @Override
    public void delete(Long clientId) {
        findById(clientId);
        clientRepository.deleteById(clientId);
    }
}
