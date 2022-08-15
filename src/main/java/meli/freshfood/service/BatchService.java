package meli.freshfood.service;

import meli.freshfood.dto.BatchDetailsDTO;
import meli.freshfood.dto.BatchDTO;
import meli.freshfood.dto.InboundOrderDTO;
import meli.freshfood.dto.BatchStockDTO;
import meli.freshfood.dto.ProductDTO;
import meli.freshfood.model.Batch;
import meli.freshfood.model.Product;
import meli.freshfood.model.InboundOrder;
import meli.freshfood.model.Section;

import java.util.List;


public interface BatchService {

	Batch findById(Long id);
	Batch save(Batch batch);
	List<Batch> filterNotExpiredProducts(List<Batch> batches);
	Integer totalAvailableBatchesCapacity(List<Batch> batches);
	boolean checkBatchAvailable(ProductDTO productDTO);
	void updateStock(ProductDTO productDTO);
	List<Batch> sortByDueDate(List<Batch> batches);
	List<BatchStockDTO> filterNotExpiredProductsByDays(List<BatchStockDTO> batches, Integer intervalDate);
	List<BatchStockDTO> findBatchesFilteredByDueDateAndSection(Integer intervalDate, Long id);
	List<Batch> findAllByProduct(Product product);
	List<BatchDetailsDTO> getBatchesByProduct(Long productId, String batchOrder);
	List<BatchDTO> createBatches(InboundOrderDTO inboundOrderDTO, Section section, InboundOrder inboundOrder);
	void updateBatches(InboundOrderDTO inboundOrderDTO, Section section, InboundOrder inboundOrder);
	List<BatchStockDTO> findBatches(Integer intervalDate, Long sectionId, String storageType, Boolean asc);
	List<BatchStockDTO> findBatchesByCategoryAndDueDate(Integer intervalDate, String storageType, Boolean order);
	List<BatchStockDTO> filterNotExpiredProductsByDaysInterval(List<BatchStockDTO> batches, Integer maxIntervalDate, Integer minIntervalDate);

	List<BatchStockDTO> filterNotExpiredProductsByDaysInterval(Integer minIntervalDate, Integer maxIntervalDate);
//	List<Batch> filterNotExpiredProductsByDaysInterval(Integer maxIntervalDate, Integer minIntervalDate);
}