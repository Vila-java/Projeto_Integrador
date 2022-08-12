package meli.freshfood.service;

import meli.freshfood.dto.BatchDTO;
import meli.freshfood.dto.InboundOrderDTO;
import meli.freshfood.model.Product;
import meli.freshfood.model.Section;
import meli.freshfood.model.Supervisor;
import meli.freshfood.model.Warehouse;

import java.util.List;

public interface SectionService {
    Section findById(Long id);
    Boolean checkSectionAvailableToStock(Section section, List<BatchDTO> batches);
    Boolean checkSectionBelongsToWarehouse(Section section, Warehouse warehouse);
    Section validatesSection(InboundOrderDTO inboundOrderDTO, Warehouse warehouse);
}
