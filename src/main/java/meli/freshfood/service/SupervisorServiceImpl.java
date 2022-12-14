package meli.freshfood.service;

import meli.freshfood.dto.InboundOrderDTO;
import meli.freshfood.exception.BadRequestException;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Supervisor;
import meli.freshfood.model.Warehouse;
import meli.freshfood.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Supervisor service.
 */
@Service
public class SupervisorServiceImpl implements SupervisorService {

    @Autowired
    private SupervisorRepository supervisorRepository;

    @Override
    public Supervisor findById(Long id) {
        return supervisorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("O supervisor não foi encontrado!"));
    }

    @Override
    public Boolean supervisorExistsInWarehouse(Supervisor supervisor, Warehouse warehouse) {
        if (supervisor.getWarehouse().getWarehouseId().equals(warehouse.getWarehouseId())) {
            return true;
        } else {
            throw new BadRequestException("O supervisor não pertence ao armazém");
        }
    }

    @Override
    public Supervisor validatesSupervisor(InboundOrderDTO inboundOrderDTO, Warehouse warehouse) {
        Supervisor supervisor = findById(inboundOrderDTO.getSupervisorId());
        supervisorExistsInWarehouse(supervisor, warehouse);
        return supervisor;
    }
}
