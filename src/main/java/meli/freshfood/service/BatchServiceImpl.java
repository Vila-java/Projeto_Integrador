package meli.freshfood.service;

import meli.freshfood.exception.InternalServerErrorException;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Batch;
import meli.freshfood.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatchServiceImpl implements BatchService {
    @Autowired
    private BatchRepository batchRepository;

    @Override
    public Optional<Batch> findById(Long id) {
        Optional<Batch> batch = batchRepository.findById(id);
        if (batch.isEmpty()) {
            throw new NotFoundException("O lote não foi encontrado!");
        }
        return batch;
    }

    @Override
    public Batch save(Batch batch) {
        return batchRepository.save(batch);
    }

    @Override
    public List<Batch> sortByDueDate(List<Batch> batches) {
        return batches.stream().sorted((b1, b2) -> {
            if(b1.getDueDate().isAfter(b2.getDueDate())) {
                return 1;
            } else {
                return 0;
            }
        }).collect(Collectors.toList());
    }

    @Override
    public List<Batch> filterNotExpiredProducts(List<Batch> batches) {
        Integer expirationDate = 3;
        return batches.stream().filter((b) ->
                b.getDueDate().isAfter(LocalDate.now().plusWeeks(expirationDate))
        ).collect(Collectors.toList());
    }
}
