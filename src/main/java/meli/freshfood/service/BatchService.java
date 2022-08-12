package meli.freshfood.service;

import meli.freshfood.dto.BatchStockDTO;
import meli.freshfood.dto.ProductDTO;
import meli.freshfood.model.Batch;

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
	List<BatchStockDTO> getByDueDate(Integer intervalDate);
}