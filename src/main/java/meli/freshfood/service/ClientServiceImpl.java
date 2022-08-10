package meli.freshfood.service;

import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Client;
import meli.freshfood.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client findById(Long id) {
       return clientRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("O cliente não foi encontrado!"));
    }
}
