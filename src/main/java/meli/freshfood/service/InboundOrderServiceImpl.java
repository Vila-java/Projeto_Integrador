package meli.freshfood.service;

import meli.freshfood.dto.BatchDTO;
import meli.freshfood.dto.InboundOrderDTO;
import meli.freshfood.model.*;
import meli.freshfood.repository.InboundOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;


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

    @Transactional
    public List<BatchDTO> create(InboundOrderDTO inboundOrderDTO){
        Section section = sectionService.findById(inboundOrderDTO.getSection().getSectionCode()).get();
        Warehouse warehouse = warehouseService.findById(inboundOrderDTO.getSection().getWarehouseCode()).get();
        Supervisor supervisor = supervisorService.findById(inboundOrderDTO.getSupervisorId()).get();
        InboundOrder inboundOrder = new InboundOrder(inboundOrderDTO, supervisor, section);
        inboundOrderRepository.save(inboundOrder);

        supervisorService.supervisorExistsInWarehouse(supervisor, warehouse);
        sectionService.checkSectionAvailableToStock(section, inboundOrderDTO.getBatchStock());
        sectionService.checkSectionBelongsToWarehouse(section,warehouse);

        List<Batch> batches = inboundOrderDTO.getBatchStock().stream().map((batchDTO) -> {
            Product product = productService.findById(batchDTO.getProductId()).get();
            sectionService.checkSectionStorageTypeIsEqualProductStorageType(section, product);
            Batch batch = new Batch(batchDTO, product, section, inboundOrder);
            return batch;
        }).collect(Collectors.toList());

        List<BatchDTO> batchesDTO = batches.stream().map((batch)-> {
            batchService.save(batch);
            return batch.toDTO();
        }).collect(Collectors.toList());

        return batchesDTO;
    }

     // TODO: Validar se o lote corresponde ao inboundOrder
    @Transactional
    public List<BatchDTO> update(InboundOrderDTO inboundOrderDTO) {
        InboundOrder inboundOrder = inboundOrderRepository.findById((Long)inboundOrderDTO.getOrderNumber()).get();
        Section section = sectionService.findById(inboundOrderDTO.getSection().getSectionCode()).get();
        Warehouse warehouse = warehouseService.findById(inboundOrderDTO.getSection().getWarehouseCode()).get();
        Supervisor supervisor = supervisorService.findById(inboundOrderDTO.getSupervisorId()).get();

        supervisorService.supervisorExistsInWarehouse(supervisor, warehouse);
        sectionService.checkSectionAvailableToStock(section, inboundOrderDTO.getBatchStock());
        sectionService.checkSectionBelongsToWarehouse(section,warehouse);

        List<Batch> batches = inboundOrderDTO.getBatchStock().stream().map((batchDTO) -> {
            Product product = productService.findById(batchDTO.getProductId()).get();
            sectionService.checkSectionStorageTypeIsEqualProductStorageType(section, product);
            Batch batch = batchService.findById(batchDTO.getBatchNumber()).get();
            batch.updateByDTO(batchDTO);
            batch.setInboundOrder(inboundOrder);
            batch.setProduct(product);
            return batch;
        }).collect(Collectors.toList());

        batches.stream().forEach((batch)-> {
            batchService.save(batch);
        });

        inboundOrder.setOrderDate(inboundOrderDTO.getOrderDate());
        inboundOrderRepository.save(inboundOrder);

        return inboundOrderDTO.getBatchStock();
    }
}
