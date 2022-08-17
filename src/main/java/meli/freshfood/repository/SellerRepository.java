package meli.freshfood.repository;

import meli.freshfood.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Seller repository.
 */
@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
}
