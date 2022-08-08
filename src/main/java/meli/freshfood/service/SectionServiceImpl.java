package meli.freshfood.service;

import meli.freshfood.model.*;
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
    public Boolean checkSectionStorageTypeIsEqualProductStorageType(Section section, Product product) {
        if (section.getStorageType().equals(product.getStorageType())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean checkSectionBelongsToWarehouse(Section section, Warehouse warehouse) {
        if (section.getWarehouse().equals(warehouse)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean checkSectionAvailableToStock(Section section, List<Batch> batches) {
        Integer totalProducts = batches.stream()
                .map((b) -> b.getCurrentQuantity())
                .reduce(0, (a,b) -> a+b);

        if(section.getProductCapacity() >= totalProducts) {
           return true;
        } else {
            return false;
        }
    }
}