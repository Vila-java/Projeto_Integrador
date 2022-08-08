package meli.freshfood.service;

import meli.freshfood.dto.InboundOrderDTO;
import meli.freshfood.model.*;
import meli.freshfood.repository.InboundOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
    private ProductService productService;

    @Autowired
    private BatchService batchService;

    public InboundOrder create(InboundOrderDTO inboundOrderDTO){

//        return inboundOrderRepository.save(inboundOrderDTO);
        return null;
    }

    public InboundOrder update(InboundOrderDTO inboundOrderDTO) {
        Warehouse warehouse = warehouseService.findById(inboundOrderDTO.getSection().getWarehouseCode()).get();
        Supervisor supervisor = supervisorService.findById(inboundOrderDTO.getSupervisorId()).get();
        Section section = sectionService.findById(inboundOrderDTO.getSection().getSectionCode()).get();

        supervisorService.supervisorExistsInWarehouse(supervisor, warehouse);
        sectionService.checkSectionAvailableToStock(section, inboundOrderDTO.getBatchStock());
        sectionService.checkSectionBelongsToWarehouse(section,warehouse);

        inboundOrderDTO.getBatchStock().stream().forEach((batchDTO) -> {
            Product product = productService.findById(batchDTO.getProductId()).get();
            sectionService.checkSectionStorageTypeIsEqualProductStorageType(section, product);
            batchService.findById(batchDTO.getBatchNumber());
        });

        inboundOrderDTO.getBatchStock().stream().forEach((batchDTO)-> {
            Batch batch = batchService.findById(batchDTO.getBatchNumber()).get();
            batch.updateByDTO(batchDTO);
        });

        return null;
        // TODO: arrumar o retorno.
  //      return inboundOrderRepository.save(inboundOrder);
    }
}
