package meli.freshfood.service;

import meli.freshfood.exception.InternalServerErrorException;
import meli.freshfood.model.Batch;
import meli.freshfood.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BatchServiceImpl implements BatchService {
    @Autowired
    private BatchRepository batchRepository;

    @Override
    public Optional<Batch> findById(Long id) {
        Optional<Batch> batch = batchRepository.findById(id);
        if (batch.isEmpty()) {
            throw new InternalServerErrorException("O lote precisa ser preenchido");
        }
        return batch;
    }

    @Override
    public Batch save(Batch batch) {
        return batchRepository.save(batch);
    }
}
