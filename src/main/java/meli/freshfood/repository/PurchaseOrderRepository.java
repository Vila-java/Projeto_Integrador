package meli.freshfood.repository;

import meli.freshfood.model.Client;
import meli.freshfood.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Purchase order repository.
 */
@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    /**
     * Find by client purchase order.
     *
     * @param client the client
     * @return the purchase order
     */
    PurchaseOrder findByClient(Client client);
}
