package meli.freshfood.service;

import meli.freshfood.dto.BatchDTO;
import meli.freshfood.dto.InboundOrderDTO;
import meli.freshfood.model.InboundOrder;

import java.util.List;

/**
 * The interface Inbound order service.
 */
public interface InboundOrderService {
    /**
     * Find by id inbound order.
     *
     * @param inboundOrderId the inbound order id
     * @return the inbound order
     */
    InboundOrder findById(Long inboundOrderId);

    /**
     * Create list.
     *
     * @param inboundOrderDTO the inbound order dto
     * @return the list
     */
    List<BatchDTO> create(InboundOrderDTO inboundOrderDTO);

    /**
     * Update list.
     *
     * @param inboundOrderDTO the inbound order dto
     * @return the list
     */
    List<BatchDTO> update(InboundOrderDTO inboundOrderDTO);
}
