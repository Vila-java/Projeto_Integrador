package meli.freshfood.service;

import meli.freshfood.model.Batch;
import meli.freshfood.model.Product;
import meli.freshfood.model.Section;

import java.util.List;
import java.util.Optional;

public interface SectionService {
    Optional<Section> findById(Long id);
    Boolean checkTypeSectionAndProduct(Section section, Product product);
    Boolean checkSectionAvailableToStock(Section section, List<Batch> batches);
}