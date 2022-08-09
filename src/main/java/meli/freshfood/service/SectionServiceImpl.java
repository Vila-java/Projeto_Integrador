package meli.freshfood.service;

import meli.freshfood.dto.BatchDTO;
import meli.freshfood.exception.InternalServerErrorException;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Product;
import meli.freshfood.model.Section;
import meli.freshfood.model.Warehouse;
import meli.freshfood.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
            throw new NotFoundException("O setor não foi encontrado!");
        }
        return section;
    }

    @Override
    public Boolean checkSectionStorageTypeIsEqualProductStorageType(Section section, Product product) {
        if (section.getStorageType().equals(product.getStorageType())) {
            return true;
        } else {
            throw new InternalServerErrorException("O setor e o produto não têm o mesmo tipo de armazenamento!");
        }
    }

    @Override
    public Boolean checkSectionBelongsToWarehouse(Section section, Warehouse warehouse) {
        if (section.getWarehouse().equals(warehouse)) {
            return true;
        } else {
            throw new InternalServerErrorException("O setor não pertence ao armazém!");
        }
    }

    @Override
    public Boolean checkSectionAvailableToStock(Section section, List<BatchDTO> batches) {
        Integer expirationDate = 3;
        Integer totalProducts = batches.stream()
                .map((b) -> {
                    if(b.getDueDate().isAfter(LocalDate.now().plusWeeks(expirationDate))) {
                        return b.getCurrentQuantity();
                    } else {
                        return 0;
                    }
                })
                .reduce(0, (a, b) -> a + b);

        if (section.getProductCapacity() >= totalProducts) {
            return true;
        } else {
            throw new InternalServerErrorException("Capacidade de armazenamento excedida!");
        }
    }
}