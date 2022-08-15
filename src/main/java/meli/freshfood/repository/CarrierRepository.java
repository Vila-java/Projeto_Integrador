package meli.freshfood.repository;

import meli.freshfood.dto.CarrierDTO;
import meli.freshfood.model.Carrier;
import meli.freshfood.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarrierRepository extends JpaRepository<Carrier, Long> {
    List<Carrier> findByVehicle(Vehicle vehicle);
}
