package meli.freshfood.service;

import meli.freshfood.dto.InboundOrderDTO;
import meli.freshfood.model.Supervisor;
import meli.freshfood.model.Warehouse;

/**
 * The interface Supervisor service.
 */
public interface SupervisorService {

    /**
     * Find by id supervisor.
     *
     * @param id the id
     * @return the supervisor
     */
    Supervisor findById(Long id);

    /**
     * Supervisor exists in warehouse boolean.
     *
     * @param supervisor the supervisor
     * @param warehouse  the warehouse
     * @return the boolean
     */
    Boolean supervisorExistsInWarehouse(Supervisor supervisor, Warehouse warehouse);

    /**
     * Validates supervisor supervisor.
     *
     * @param inboundOrderDTO the inbound order dto
     * @param warehouse       the warehouse
     * @return the supervisor
     */
    Supervisor validatesSupervisor(InboundOrderDTO inboundOrderDTO, Warehouse warehouse);
}
