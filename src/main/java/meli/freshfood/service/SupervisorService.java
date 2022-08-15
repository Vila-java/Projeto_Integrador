package meli.freshfood.service;

import meli.freshfood.dto.InboundOrderDTO;
import meli.freshfood.model.Supervisor;
import meli.freshfood.model.Warehouse;

import java.util.List;

public interface SupervisorService {

    Supervisor findById(Long id);
    Boolean supervisorExistsInWarehouse(Supervisor supervisor, Warehouse warehouse);
    Supervisor validatesSupervisor(InboundOrderDTO inboundOrderDTO, Warehouse warehouse);
    Supervisor create(Supervisor supervisor);
    List<Supervisor> findAll();
    Supervisor update(Supervisor supervisor);
    void delete (Long supersivorId);
}
