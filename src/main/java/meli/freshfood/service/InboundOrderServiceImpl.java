package meli.freshfood.service;

import meli.freshfood.model.InboundOrder;
import meli.freshfood.repository.InboundOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class InboundOrderServiceImpl implements InboundOrderService {

    @Autowired
    private InboundOrderRepository inboundOrderRepository;

    public InboundOrder create(InboundOrder inboundOrder){
        return inboundOrderRepository.save(inboundOrder);
    }

}
