package meli.freshfood.service;

import meli.freshfood.dto.InboundOrderDTO;
import meli.freshfood.model.InboundOrder;
import meli.freshfood.model.Supervisor;
import meli.freshfood.model.Warehouse;

public interface SupervisorService {

    Supervisor findById(Long id);
    Boolean supervisorExistsInWarehouse(Supervisor supervisor, Warehouse warehouse);
    Supervisor validatesSupervisor(InboundOrderDTO inboundOrderDTO, Warehouse warehouse);
}
