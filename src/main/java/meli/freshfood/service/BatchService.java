package meli.freshfood.service;

import meli.freshfood.dto.*;
import meli.freshfood.model.Batch;
import meli.freshfood.model.InboundOrder;
import meli.freshfood.model.Product;
import meli.freshfood.model.Section;

import java.util.List;


/**
 * The interface Batch service.
 */
public interface BatchService {

    /**
     * Find by id batch.
     *
     * @param id the id
     * @return the batch
     */
    Batch findById(Long id);

    /**
     * Save batch.
     *
     * @param batch the batch
     * @return the batch
     */
    Batch save(Batch batch);

    /**
     * Filter not expired products list.
     *
     * @param batches the batches
     * @return the list
     */
    List<Batch> filterNotExpiredProducts(List<Batch> batches);

    /**
     * Total available batches capacity integer.
     *
     * @param batches the batches
     * @return the integer
     */
    Integer totalAvailableBatchesCapacity(List<Batch> batches);

    /**
     * Check batch available boolean.
     *
     * @param productDTO the product dto
     * @return the boolean
     */
    boolean checkBatchAvailable(ProductDTO productDTO);

    /**
     * Update stock.
     *
     * @param productDTO the product dto
     */
    void updateStock(ProductDTO productDTO);

    /**
     * Sort by due date list.
     *
     * @param batches the batches
     * @return the list
     */
    List<Batch> sortByDueDate(List<Batch> batches);

    /**
     * Find all by product list.
     *
     * @param product the product
     * @return the list
     */
    List<Batch> findAllByProduct(Product product);

    /**
     * Gets batches by product.
     *
     * @param productId  the product id
     * @param batchOrder the batch order
     * @return the batches by product
     */
    List<BatchDetailsDTO> getBatchesByProduct(Long productId, String batchOrder);

    /**
     * Create batches list.
     *
     * @param inboundOrderDTO the inbound order dto
     * @param section         the section
     * @param inboundOrder    the inbound order
     * @return the list
     */
    List<BatchDTO> createBatches(InboundOrderDTO inboundOrderDTO, Section section, InboundOrder inboundOrder);

    /**
     * Update batches.
     *
     * @param inboundOrderDTO the inbound order dto
     * @param section         the section
     * @param inboundOrder    the inbound order
     */
    void updateBatches(InboundOrderDTO inboundOrderDTO, Section section, InboundOrder inboundOrder);

    /**
     * Find batches list.
     *
     * @param intervalDate the interval date
     * @param sectionId    the section id
     * @param storageType  the storage type
     * @param asc          the asc
     * @return the list
     */
    List<BatchStockDTO> findBatches(Integer intervalDate, Long sectionId, String storageType, Boolean asc);

}