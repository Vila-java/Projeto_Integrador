package meli.freshfood.repository;

import meli.freshfood.model.InboundOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Inbound order repository.
 */
@Repository
public interface InboundOrderRepository extends JpaRepository<InboundOrder, Long> {
    /**
     * Find by batch inbound order.
     *
     * @param id the id
     * @return the inbound order
     */
    InboundOrder findByBatch(Long id);
}
