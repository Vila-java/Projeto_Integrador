package meli.freshfood.service;

import meli.freshfood.model.Batch;
import meli.freshfood.model.Product;
import meli.freshfood.model.Section;
import meli.freshfood.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public Optional<Section> findById(Long id) {
        Optional<Section> section = sectionRepository.findById(id);
        if (section.isEmpty()) {
            // TODO: Alterar exceção
            throw new RuntimeException();
        }
        return section;
    }

    @Override
    public Boolean checkTypeSectionAndProduct(Section section, Product product) {
        if (section.getStorageType().equals(product.getStorageType())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean checkSectionAvailableToStock(Section section, List<Batch> batches) {
        Integer totalArea = batches.stream()
                .map((b) -> b.getCurrentQuantity())
                .reduce(0, (a,b) -> a+b);

        if(section.getProductCapacity() >= totalArea) {
           return true;
        } else {
            return false;
        }
    }
}