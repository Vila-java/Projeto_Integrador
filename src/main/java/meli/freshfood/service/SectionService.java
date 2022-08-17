package meli.freshfood.service;

import meli.freshfood.dto.BatchDTO;
import meli.freshfood.dto.InboundOrderDTO;
import meli.freshfood.model.Section;
import meli.freshfood.model.Warehouse;

import java.util.List;

/**
 * The interface Section service.
 */
public interface SectionService {
    /**
     * Find by id section.
     *
     * @param id the id
     * @return the section
     */
    Section findById(Long id);

    /**
     * Check section available to stock boolean.
     *
     * @param section the section
     * @param batches the batches
     * @return the boolean
     */
    Boolean checkSectionAvailableToStock(Section section, List<BatchDTO> batches);

    /**
     * Check section belongs to warehouse boolean.
     *
     * @param section   the section
     * @param warehouse the warehouse
     * @return the boolean
     */
    Boolean checkSectionBelongsToWarehouse(Section section, Warehouse warehouse);

    /**
     * Validates section section.
     *
     * @param inboundOrderDTO the inbound order dto
     * @param warehouse       the warehouse
     * @return the section
     */
    Section validatesSection(InboundOrderDTO inboundOrderDTO, Warehouse warehouse);
}
