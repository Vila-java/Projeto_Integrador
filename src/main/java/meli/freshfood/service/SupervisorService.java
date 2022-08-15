package meli.freshfood.service;

import meli.freshfood.dto.InboundOrderDTO;
import meli.freshfood.dto.SupervisorDetailsDTO;
import meli.freshfood.model.Supervisor;
import meli.freshfood.model.Warehouse;

import java.util.List;

public interface SupervisorService {

    Supervisor findById(Long id);
    Boolean supervisorExistsInWarehouse(Supervisor supervisor, Warehouse warehouse);
    Supervisor validatesSupervisor(InboundOrderDTO inboundOrderDTO, Warehouse warehouse);
    Supervisor create(Supervisor supervisor);
    List<Supervisor> findAll();
    Supervisor updateById(Supervisor supervisor);
    void deleteById(Long supersivorId);
    List<SupervisorDetailsDTO> findAllByWarehouseSupervisor(String warehouseSupervisor);
}
