package meli.freshfood.repository;

import meli.freshfood.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Section repository.
 */
@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
}
