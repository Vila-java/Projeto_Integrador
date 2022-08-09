package meli.freshfood.service;

import meli.freshfood.dto.BatchDTO;
import meli.freshfood.dto.InboundOrderDTO;
import meli.freshfood.model.InboundOrder;

import java.util.List;

public interface InboundOrderService {
    List<BatchDTO> create(InboundOrderDTO inboundOrderDTO);
    List<BatchDTO> update(InboundOrderDTO inboundOrderDTO);
}
