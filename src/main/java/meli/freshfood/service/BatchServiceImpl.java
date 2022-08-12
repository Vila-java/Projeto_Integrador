package meli.freshfood.service;

import meli.freshfood.dto.ProductDTO;
import meli.freshfood.exception.BadRequestException;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Batch;
import meli.freshfood.model.Product;
import meli.freshfood.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatchServiceImpl implements BatchService {

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private ProductService productService;

    @Override
    public Batch findById(Long id) {
        return batchRepository.findById(id)
       .orElseThrow(() -> new NotFoundException("O lote não foi encontrado!"));
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

    @Override
    public Integer totalAvailableBatchesCapacity(List<Batch> batches) {
        List<Batch> batchesAvailable = filterNotExpiredProducts(batches);
        Integer totalAvailableCapacity = batchesAvailable.stream()
                .map((b) -> b.getCurrentQuantity())
                .reduce(0, (b1, b2) -> b1 + b2);
        return totalAvailableCapacity;
    }

    @Override
    public boolean checkBatchAvailable(ProductDTO productDTO) {
        Product product = productService.findById(productDTO.getProductId());
        Integer totalCapacityAvailable = this.totalAvailableBatchesCapacity(product.getBatches());

        if (totalCapacityAvailable < productDTO.getQuantity()) {
            throw new BadRequestException("Estoque indisponível para quantidade de produto solicitada!");
        }
        return true;
    }

    // Método faz o controle de estoque dos lotes,
    // removendo primeiramente os lotes que estão com data de vencimento próxima
    @Override
    public void updateStock(ProductDTO productDTO) {
        checkBatchAvailable(productDTO);
        Product product = productService.findById(productDTO.getProductId());

        final int[] purchaseQuantity = new int[1];
        purchaseQuantity[0] = productDTO.getQuantity();

        List<Batch> batchesDueDateSorted = sortByDueDate(product.getBatches());

        batchesDueDateSorted.forEach(batch -> {
            if(batch.getCurrentQuantity() > 0 && purchaseQuantity[0] > 0) {
                if(batch.getCurrentQuantity() >= purchaseQuantity[0]) {
                    Integer stockBatch = batch.getCurrentQuantity();
                    batch.setCurrentQuantity(stockBatch - purchaseQuantity[0]);
                    purchaseQuantity[0] -= stockBatch;
                } else {
                    purchaseQuantity[0] -= batch.getCurrentQuantity();
                    batch.setCurrentQuantity(0);
                }
                this.save(batch);
            }
        });
    }

    @Override
    public List<Batch> filterNotExpiredProductsByDays(List<Batch> batches, Integer intervalDate) {
        return batches.stream().filter((b) -> {
                LocalDate dueDate = b.getDueDate();
                if(dueDate.isAfter(LocalDate.now()) &&
                        dueDate.isBefore(LocalDate.now().plusDays(intervalDate))) {
                    return true;
                } else {
                    return false;
                }
            }).collect(Collectors.toList());
    }


    //Retorna todos os lotes armazenados em um setor de um armazém dentro de um intervalo de datas filtrados pela data de vencimento
    @Override
    public List<Batch> getByDueDate(List<Batch> batches, Integer intervalDate) {
        return filterNotExpiredProductsByDays(batches, intervalDate);
    }

}
