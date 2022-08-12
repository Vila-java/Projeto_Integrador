package meli.freshfood.service;

import meli.freshfood.dto.BatchStockDTO;
import meli.freshfood.dto.BatchDTO;
import meli.freshfood.dto.InboundOrderDTO;
import meli.freshfood.dto.BatchDetailsDTO;
import meli.freshfood.dto.ProductDTO;
import meli.freshfood.exception.BadRequestException;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Batch;
import meli.freshfood.model.InboundOrder;
import meli.freshfood.model.Product;
import meli.freshfood.model.Section;
import meli.freshfood.model.Warehouse;
import meli.freshfood.model.Section;
import meli.freshfood.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
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

    public List<Batch> findAllByProduct(Product product){
        return batchRepository.findAllByProduct(product);
    }

    @Override
    public Batch save(Batch batch) {
        return batchRepository.save(batch);
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

        // TODO: Verificar se existe uma solução melhor
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
    public List<BatchStockDTO> filterNotExpiredProductsByDays(List<BatchStockDTO> batches, Integer intervalDate) {
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
    public List<BatchStockDTO> getByDueDate(Integer intervalDate) {
        List<Batch> batches = batchRepository.findAll();
        List<BatchStockDTO> batchesDTO = batches.stream().map(batch -> batch.toBatchStockDTO()).collect(Collectors.toList());
        return filterNotExpiredProductsByDays(batchesDTO, intervalDate);
    }


    @Override
    public List<BatchDetailsDTO> getBatchesByProduct(Long productId, String batchOrder) {
        Product product = productService.findById(productId);

        List<Batch> batches = product.getBatches();
        if(batchOrder != null) {
            batches = sort(batches, batchOrder);
        }

        List<BatchDetailsDTO> batchesDTO = batches.stream()
                .map((batch) -> {
                    Section section = batch.getSection();
                    Warehouse warehouse = section.getWarehouse();
                    return new BatchDetailsDTO(
                            batch.getBatchNumber(), batch.getCurrentTemperature(),
                            batch.getCurrentQuantity(), batch.getDueDate(),
                            warehouse.getWarehouseId(), section.getSectionId()
                    );
        }).collect(Collectors.toList());

        return batchesDTO;
    }

    public List<Batch> sort(List<Batch> batches, String batchOrder) {
        if(batchOrder.equalsIgnoreCase("Q")) {
            return sortByCurrentQuantity(batches);
        } else if(batchOrder.equalsIgnoreCase("V")) {
            return sortByDueDate(batches);
        } else if(batchOrder.equalsIgnoreCase("L")) {
            return sortByBatchNumber(batches);
        } else {
            throw new BadRequestException("Não existe esse tipo de ordenação!");
        }
    }

    public List<Batch> sortByCurrentQuantity(List<Batch> batches) {
        return batches.stream().sorted(Comparator.comparing(Batch::getCurrentQuantity)).collect(Collectors.toList());
    }

    public List<Batch> sortByDueDate(List<Batch> batches) {
        return batches.stream().sorted(Comparator.comparing(Batch::getDueDate)).collect(Collectors.toList());
    }

    public List<Batch> sortByBatchNumber(List<Batch> batches) {
        return batches.stream().sorted(Comparator.comparing(Batch::getBatchNumber)).collect(Collectors.toList());
    }

    @Override
    public List<BatchDTO> createBatches(InboundOrderDTO inboundOrderDTO, Section section, InboundOrder inboundOrder) {
        // valida
        List<Batch> batches = inboundOrderDTO.getBatchStock().stream().map((batchDTO) -> {
            Product product = productService.findById(batchDTO.getProductId());
            productService.checkProductStorageIsEqualSectionStorage(product, section);
            Batch batch = new Batch(batchDTO, product, section, inboundOrder);
            return batch;
        }).collect(Collectors.toList());

        // salva
        return batches.stream().map((batch)-> {
            save(batch);
            return batch.toDTO();
        }).collect(Collectors.toList());
    }

    @Override
    public void updateBatches(InboundOrderDTO inboundOrderDTO, Section section, InboundOrder inboundOrder) {
        List<Batch> batches = inboundOrderDTO.getBatchStock().stream().map((batchDTO) -> {
            Product product = productService.findById(batchDTO.getProductId());
            productService.checkProductStorageIsEqualSectionStorage(product, section);
            Batch batch = findById(batchDTO.getBatchNumber());
            batch.updateByDTO(batchDTO);
            batch.setInboundOrder(inboundOrder);
            batch.setProduct(product);
            return batch;
        }).collect(Collectors.toList());

        batches.stream().forEach((batch)-> {
            save(batch);
        });
    }
}
