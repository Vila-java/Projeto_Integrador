package meli.freshfood.service;

import meli.freshfood.model.Client;

/**
 * The interface Client service.
 */
public interface ClientService {

    /**
     * Find by id client.
     *
     * @param id the id
     * @return the client
     */
    Client findById(Long id);
}
