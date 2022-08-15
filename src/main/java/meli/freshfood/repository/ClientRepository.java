package meli.freshfood.repository;

import meli.freshfood.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // @Query(value = "SELECT * FROM WHERE", nativeQuery = true)
}
