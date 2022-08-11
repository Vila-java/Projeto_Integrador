package meli.freshfood.service;

import meli.freshfood.dto.BatchDTO;
import meli.freshfood.model.Product;
import meli.freshfood.model.Section;
import meli.freshfood.model.Warehouse;

import java.util.List;

public interface SectionService {
    Section findById(Long id);
    Boolean checkSectionStorageTypeIsEqualProductStorageType(Section section, Product product);
    Boolean checkSectionAvailableToStock(Section section, List<BatchDTO> batches);
    Boolean checkSectionBelongsToWarehouse(Section section, Warehouse warehouse);
}
