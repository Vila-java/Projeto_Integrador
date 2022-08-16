package meli.freshfood.service;

import meli.freshfood.dto.BatchDTO;
import meli.freshfood.dto.InboundOrderDTO;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.InboundOrder;
import meli.freshfood.model.Section;
import meli.freshfood.model.Supervisor;
import meli.freshfood.model.Warehouse;
import meli.freshfood.repository.InboundOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InboundOrderServiceImpl implements InboundOrderService {

    @Autowired
    private InboundOrderRepository inboundOrderRepository;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private SupervisorService supervisorService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private BatchService batchService;

    @Override
    public InboundOrder findById(Long inboundOrderId) {
        return inboundOrderRepository.findById(inboundOrderId)
                .orElseThrow(() -> new NotFoundException("Não existe essa relação!"));
    }

    @Override
    public List<BatchDTO> create(InboundOrderDTO inboundOrderDTO){
        Warehouse warehouse = warehouseService.findById(inboundOrderDTO.getSection().getWarehouseCode());
        Section section = sectionService.validatesSection(inboundOrderDTO, warehouse);
        Supervisor supervisor = supervisorService.validatesSupervisor(inboundOrderDTO, warehouse);
        InboundOrder inboundOrder = new InboundOrder(inboundOrderDTO, supervisor, section);
        inboundOrderRepository.save(inboundOrder);

        return batchService.createBatches(inboundOrderDTO, section, inboundOrder);
    }

    @Override
    public List<BatchDTO> update(InboundOrderDTO inboundOrderDTO) {
        Warehouse warehouse = warehouseService.findById(inboundOrderDTO.getSection().getWarehouseCode());
        Section section = sectionService.validatesSection(inboundOrderDTO, warehouse);
        Supervisor supervisor = supervisorService.validatesSupervisor(inboundOrderDTO, warehouse);
        InboundOrder inboundOrder = findById(inboundOrderDTO.getOrderNumber());

        batchService.updateBatches(inboundOrderDTO, section, inboundOrder);

        inboundOrder.setOrderDate(inboundOrderDTO.getOrderDate());
        inboundOrderRepository.save(inboundOrder);

        return inboundOrderDTO.getBatchStock();
    }
}
