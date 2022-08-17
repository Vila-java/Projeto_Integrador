package meli.freshfood.repository;

import meli.freshfood.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Client repository.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
