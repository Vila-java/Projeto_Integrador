package meli.freshfood.repository;

import meli.freshfood.model.InboundOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InboundOrderRepository extends JpaRepository<InboundOrder, Long> {
    InboundOrder findByBatch(Long id);
}
