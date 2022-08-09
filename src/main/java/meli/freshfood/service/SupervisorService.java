package meli.freshfood.service;

import meli.freshfood.model.Supervisor;
import meli.freshfood.model.Warehouse;

import java.util.Optional;

public interface SupervisorService {

    Optional<Supervisor> findById(Long id);
    Boolean supervisorExistsInWarehouse(Supervisor supervisor, Warehouse warehouse);
}
