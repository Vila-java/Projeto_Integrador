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

    public InboundOrder create(InboundOrder inboundOrder){
        return inboundOrderRepository.save(inboundOrder);
    }

    public InboundOrder update(InboundOrderDTO inboundOrderDTO) {
        Warehouse warehouse = warehouseService.findById(inboundOrderDTO.getSection().getWarehouseCode()).get();
        Supervisor supervisor = supervisorService.findById(inboundOrderDTO.getSupervisorId()).get();
        if (!supervisorService.supervisorExistsInWarehouse(supervisor, warehouse)) {
            // TODO: Alterar exceção
            throw new RuntimeException();
        }

        Section section = sectionService.findById(inboundOrderDTO.getSection().getSectionCode()).get();

        // TODO: Verificar possibilidade de fazer em um único loop
        if(!sectionService.checkSectionAvailableToStock(section, inboundOrderDTO.getBatchStock())) {
            // TODO: Alterar exceção
            throw new RuntimeException();
        }

        if (!sectionService.checkSectionBelongsToWarehouse(section,warehouse)){
            // TODO: Alterar exceção
            throw new RuntimeException();
        }

        inboundOrderDTO.getBatchStock().stream().forEach((batchDTO)-> {
            Product product = productService.findById(batchDTO.getProductId()).get();
            if (!sectionService.checkSectionStorageTypeIsEqualProductStorageType(section, product)){
                // TODO: Alterar exceção
                throw new RuntimeException();
            }
            batchService.findById(batchDTO.getBatchNumber());
        });

        inboundOrderDTO.getBatchStock().stream().forEach((batchDTO)-> {
            Batch batch = batchService.findById(batchDTO.getBatchNumber()).get();

        });

        return null;
    }
}
