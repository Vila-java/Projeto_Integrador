package meli.freshfood.service;

import meli.freshfood.dto.InboundOrderDTO;
import meli.freshfood.model.InboundOrder;

public interface InboundOrderService {
    InboundOrder create(InboundOrderDTO inboundOrderDTO);
    InboundOrder update(InboundOrderDTO inboundOrderDTO);
}
