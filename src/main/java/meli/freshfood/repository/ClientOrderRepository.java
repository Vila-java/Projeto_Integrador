package meli.freshfood.repository;

import meli.freshfood.model.Client;
import meli.freshfood.model.ClientOrder;
import meli.freshfood.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClientOrderRepository extends JpaRepository<ClientOrder, Long> {
    List<ClientOrder> findByClient(Client client);
}
