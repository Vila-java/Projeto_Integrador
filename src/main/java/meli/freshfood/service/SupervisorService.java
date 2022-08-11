package meli.freshfood.service;

import meli.freshfood.model.Supervisor;
import meli.freshfood.model.Warehouse;

public interface SupervisorService {

    Supervisor findById(Long id);
    Boolean supervisorExistsInWarehouse(Supervisor supervisor, Warehouse warehouse);
}
