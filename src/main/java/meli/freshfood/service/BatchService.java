package meli.freshfood.service;

import meli.freshfood.model.Batch;

import java.util.Optional;

public interface BatchService {
	Optional<Batch> findById(Long id);
}