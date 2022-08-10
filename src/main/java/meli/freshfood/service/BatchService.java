package meli.freshfood.service;

import meli.freshfood.model.Batch;

import java.util.List;
import java.util.Optional;

public interface BatchService {
	Optional<Batch> findById(Long id);
	Batch save(Batch batch);
	List<Batch> sortByDueDate(List<Batch> batches);
	List<Batch> filterNotExpiredProducts(List<Batch> batches);
	Integer totalAvailableBatchesCapacity(List<Batch> batches);
}