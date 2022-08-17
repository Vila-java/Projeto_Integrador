package meli.freshfood.repository;

import meli.freshfood.model.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Supervisor repository.
 */
@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {
}
