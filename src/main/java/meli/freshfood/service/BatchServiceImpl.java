package meli.freshfood.service;

import meli.freshfood.dto.BatchStockDTO;
import meli.freshfood.dto.BatchDTO;
import meli.freshfood.dto.BatchDetailsDTO;
import meli.freshfood.dto.InboundOrderDTO;
import meli.freshfood.dto.ProductDTO;
import meli.freshfood.dto.*;
import meli.freshfood.exception.BadRequestException;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.*;
import meli.freshfood.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;
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

    public List<Batch> findAllByProduct(Product product) {
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
        return batchesAvailable.stream()
                .map((b) -> b.getCurrentQuantity())
                .reduce(0, (b1, b2) -> b1 + b2);
    }

    @Override
    public boolean checkBatchAvailable(ProductDTO productDTO) {
        Product product = productService.findById(productDTO.getProductId());
        Integer totalCapacityAvailable = totalAvailableBatchesCapacity(product.getBatches());

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
            if (batch.getCurrentQuantity() > 0 && purchaseQuantity[0] > 0) {
                if (batch.getCurrentQuantity() >= purchaseQuantity[0]) {
                    Integer stockBatch = batch.getCurrentQuantity();
                    batch.setCurrentQuantity(stockBatch - purchaseQuantity[0]);
                    purchaseQuantity[0] -= stockBatch;
                } else {
                    purchaseQuantity[0] -= batch.getCurrentQuantity();
                    batch.setCurrentQuantity(0);
                }
                save(batch);
            }
        });
    }


    //Retorna todos os lotes armazenados em um setor de um armazém dentro de um intervalo de datas filtrados pela data de vencimento
    @Override
    public List<BatchDetailsDTO> getBatchesByProduct(Long productId, String batchOrder) {
        Product product = productService.findById(productId);

        List<Batch> batches = product.getBatches();
        if (batchOrder != null) {
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
        if (batchOrder.equalsIgnoreCase("Q")) {
            return sortByCurrentQuantity(batches);
        } else if (batchOrder.equalsIgnoreCase("V")) {
            return sortByDueDate(batches);
        } else if (batchOrder.equalsIgnoreCase("L")) {
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
        return batches.stream().map((batch) -> {
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

        batches.stream().forEach((batch) -> {
            save(batch);
        });
    }

    public List<Batch> filterByCategory(List<Batch> batches, String storageType) {
        if (storageType != null) {
            StorageType storageTypeObj = StorageType.parseToStorage(storageType);
            return batches.stream().filter(b -> b.getProduct()
                    .getStorageType()
                    .equals(storageTypeObj))
                    .collect(Collectors.toList());
        }
        return batches;
    }

    public List<Batch> filterBySection(List<Batch> batches, Long id) {

        if (id != null) {
            List<Batch> batchesFiltered = batches
                    .stream().filter((b) -> b.getSection().getSectionId().equals(id))
                    .collect(Collectors.toList());
            return batchesFiltered;
        }
        return batches;
    }

    public List<Batch> filterDueDateInterval(List<Batch> batches, Integer intervalDate) {
        return batches.stream().filter((b) -> {
            LocalDate dueDate = b.getDueDate();
            return dueDate.isAfter(LocalDate.now()) &&
                    dueDate.isBefore(LocalDate.now().plusDays(intervalDate));
        }).collect(Collectors.toList());
    }

    public List<Batch> sortByDueDateAscOrDesc(List<Batch> batches, Boolean asc) {
        if (asc) {
            return batches.stream().sorted(Comparator.comparing(Batch::getDueDate)).collect(Collectors.toList());
        } else {
            return batches.stream().sorted(Comparator.comparing(Batch::getDueDate).reversed()).collect(Collectors.toList());
        }
    }

    @Override
    public List<BatchStockDTO> findBatches(Integer intervalDate, Long sectionId, String storageType, Boolean asc) {
        List<Batch> batches = batchRepository.findAll();
        batches = filterDueDateInterval(batches, intervalDate);
        batches = filterBySection(batches, sectionId);
        batches = filterByCategory(batches, storageType);
        batches = sortByDueDateAscOrDesc(batches, asc);

        return batches.stream().map(Batch::toBatchStockDTO).collect(Collectors.toList());
    }

//    public List<Batch> filterNotExpiredProductsByDaysInterval(List<Batch> batches, Integer maxIntervalDate, Integer minIntervalDate) {
//        List<Batch> sortDueDate = sortByDueDate(batches);
//        return batches.stream()
//                .filter((b) -> {
//                    LocalDate dueDate = b.getDueDate();
//                    if (dueDate.isAfter(LocalDate.now()) &&
//                            dueDate.isBefore(LocalDate.now().plusDays(minIntervalDate)) &&
//                            dueDate.isBefore(LocalDate.now().plusDays(maxIntervalDate))) {
//                        return true;
//                    } else {
//                        return false;
//                    }
//                }).collect(Collectors.toList());
//    }

//        public List<Batch> filterNotExpiredProductsByDaysInterval(List<Batch> batches, Integer maxIntervalDate, Integer minIntervalDate) {
//        List<Batch> sortDueDate = sortByDueDate(batches);
//        return sortDueDate.stream()
//                .filter((b) -> {
//                    LocalDate dueDate = b.getDueDate();
//                    if (dueDate.isBefore(LocalDate.now().plusDays(minIntervalDate)) &&
//                            dueDate.isBefore(LocalDate.now().plusDays(maxIntervalDate))) {
//                        return true;
//                    } else {
//                        return false;
//                    }
//                }).collect(Collectors.toList());
//    }

    public List<Batch> filterDueDateInterval21(List<Batch> batches) {
        Integer intervalDateMax = 21;
//        Integer intervalDateMin = 15;
        return batches.stream().filter((b) -> {
            LocalDate dueDate = b.getDueDate();
            return dueDate.isAfter(LocalDate.now()) &&
                    dueDate.isBefore(LocalDate.now().plusDays(intervalDateMax));
//                    &&
//                    dueDate.isBefore(LocalDate.now().plusDays(intervalDateMin));
        }).collect(Collectors.toList());
    }

    public List<Batch> filterDueDateInterval15(List<Batch> batches) {
        Integer intervalDate = 15;
        return batches.stream().filter((b) -> {
            LocalDate dueDate = b.getDueDate();
            return dueDate.isAfter(LocalDate.now()) &&
                    dueDate.isBefore(LocalDate.now().plusDays(intervalDate));
        }).collect(Collectors.toList());
    }

    public List<Batch> filterDueDateInterval7(List<Batch> batches) {
        Integer intervalDate = 7;
        return batches.stream().filter((b) -> {
            LocalDate dueDate = b.getDueDate();
            return dueDate.isAfter(LocalDate.now()) &&
                    dueDate.isBefore(LocalDate.now().plusDays(intervalDate));
        }).collect(Collectors.toList());
    }

//    @Override
//    public List<ProductPromotionDTO> findBatchesFilteredByDueDateAndPromotion(Integer maxIntervalDate, Integer minIntervalDate) {
//        List<Batch> batches = batchRepository.findAll();
//        List<Batch> batchesFiltered = filterNotExpiredProductsByDaysInterval(batches, maxIntervalDate, minIntervalDate);
//        List<ProductPromotionDTO> productPromotion = new ArrayList<>();
//
//        BigDecimal desc30 = new BigDecimal(0.30);
//        BigDecimal desc60 = new BigDecimal(0.60);
//
//        if (maxIntervalDate >= 21 && minIntervalDate <= 15) {
//            batchesFiltered.stream().forEach(b -> productPromotion.stream()
//                    .forEach(pP -> pP.setPricePromotion(b.getProduct().getPrice()
//                            .subtract(b.getProduct().getPrice().multiply(desc30)))));
//        } else if (maxIntervalDate >= 15 && minIntervalDate <= 07) {
//            batchesFiltered.stream().forEach(b -> productPromotion.stream()
//                    .forEach(pP -> pP.setPricePromotion(b.getProduct().getPrice()
//                            .subtract(b.getProduct().getPrice().multiply(desc60)))));
//        } else if (maxIntervalDate > 7 && minIntervalDate < 0) {
//            return null;
////        } else {
////            throw new NotFoundException("Produto está com menos de 7 dias para o vencimento, precisa ser descartado!");
//        }

//    @Override
//    public List<ProductPromotionDTO> findBatchesFilteredByDueDateAndPromotion(Integer maxIntervalDate, Integer minIntervalDate) {
//        List<Batch> batches = batchRepository.findAll();
//        List<Batch> batchesFiltered = filterNotExpiredProductsByDaysInterval(batches, maxIntervalDate, minIntervalDate);
//        List<ProductPromotionDTO> productPromotionList = new ArrayList<>();
//
//        productPromotionList = batchesFiltered.stream().map(b -> b.toProductPromotionDTO(b.getProduct().getProductId(), b.getProduct().getName(),
//                b.getDueDate(), b.getProduct().getPrice(), new BigDecimal(0), " ")).collect(Collectors.toList());
//
//        BigDecimal desc30 = new BigDecimal(0.30);
//        BigDecimal desc60 = new BigDecimal(0.60);
//
//        for (ProductPromotionDTO productPromotion : productPromotionList) {
//            LocalDate dueDate = productPromotion.getDueDate();
//            Period periodo = Period.between(dueDate,LocalDate.now());
//            int dias = periodo.getDays();
//            int meses = periodo.getMonths();
//            Long differenceDays = productPromotion.getDueDate().until(LocalDate.now(), ChronoUnit.DAYS);
//
//            if (differenceDays >= 15 && differenceDays <= 21) {
//                productPromotion.setPricePromotion(productPromotion.getPrice().subtract(productPromotion.getPrice().multiply(desc30)));
//                productPromotion.setMessage("O produto está em promoção com 30% de desconto.");
//            } else if (differenceDays >= 07 && differenceDays <= 14) {
//                productPromotion.setPricePromotion(productPromotion.getPrice().subtract(productPromotion.getPrice().multiply(desc60)));
//                productPromotion.setMessage("O produto está em promoção com 60% de desconto.");
//            } else if (differenceDays <= 6) {
//                productPromotion.setMessage(
//                        ("O produto está com menos de 7 dias da data de vencimento, descarte."));
//            }
//        }
//            return productPromotionList;
//    }



//    @Override
//    public List<ProductPromotionDTO> findBatchesFilteredByDueDateAndPromotion(Integer maxIntervalDate, Integer minIntervalDate) {
//        List<Batch> batches = batchRepository.findAll();
//        List<Batch> batchesFiltered = filterDueDateInterval21(batches);
//        List<ProductPromotionDTO> productPromotionList = new ArrayList<>();
//
//        productPromotionList = batchesFiltered.stream().map(b -> b.toProductPromotionDTO(b.getProduct().getProductId(), b.getProduct().getName(),
//                b.getDueDate(), b.getProduct().getPrice(), new BigDecimal(0), " ")).collect(Collectors.toList());
//
//        BigDecimal desc30 = new BigDecimal(0.30);
//        BigDecimal desc60 = new BigDecimal(0.60);
//
//        for (ProductPromotionDTO productPromotion : productPromotionList) {
//            LocalDate dueDate = productPromotion.getDueDate();
//            Period periodo = Period.between(dueDate, LocalDate.now());
//            int dias = periodo.getDays();
//            int meses = periodo.getMonths();
//            Long differenceDays = dias + meses * 30L;
//
//            if (differenceDays >= 15 && differenceDays <= 21) {
//                productPromotionList.stream().forEach(p -> p.setPricePromotion(p.getPrice()
//                        .subtract(p.getPrice().multiply(desc30))));
//                productPromotionList.stream().forEach(p -> p.setMessage("O produto está em promoção com 30% de desconto."));
//            } else if (differenceDays >= 07 && differenceDays <= 15) {
//                productPromotionList.stream().forEach(p -> p.setPricePromotion(p.getPrice()
//                        .subtract(p.getPrice().multiply(desc60))));
//                productPromotionList.stream().forEach(p -> p.setMessage("O produto está em promoção com 60% de desconto."));
//            } else if (differenceDays < 7) {
//                productPromotionList.stream().forEach(p -> p.setMessage
//                        ("O produto está com menos de 7 dias da data de vencimento, descarte."));
//            }
//        }
//            return productPromotionList;
//
//    }


    @Override
    public List<ProductPromotionDTO> findBatchesFilteredByPromotion30() {
        List<Batch> batches = batchRepository.findAll();
        List<Batch> batchesFiltered = filterDueDateInterval21(batches);
        List<ProductPromotionDTO> productPromotion = batchesFiltered.stream()
                .map(b -> b.toProductPromotionDTO(b.getProduct().getProductId(), b.getProduct().getName(),
                        b.getDueDate(), b.getProduct().getPrice(), new BigDecimal("0"), " "))
                .collect(Collectors.toList());

        BigDecimal desc30 = new BigDecimal("0.3");

        productPromotion.stream().forEach(p -> p.setPricePromotion(p.getPrice()
                .subtract(p.getPrice().multiply(desc30))));
        productPromotion.stream().forEach(p -> p.setMessage("O produto está em promoção com 30% de desconto."));

        return productPromotion;

    }

    @Override
    public List<ProductPromotionDTO> findBatchesFilteredByPromotion60() {
        List<Batch> batches = batchRepository.findAll();
        List<Batch> batchesFiltered = filterDueDateInterval15(batches);
        List<ProductPromotionDTO> productPromotion = batchesFiltered.stream()
                .map(b -> b.toProductPromotionDTO(b.getProduct().getProductId(), b.getProduct().getName(),
                        b.getDueDate(), b.getProduct().getPrice(), new BigDecimal("0"), " "))
                .collect(Collectors.toList());
//        NumberFormat format = NumberFormat.getPercentInstance();
//
//        format.setMaximumFractionDigits(2);
//        format.setMinimumFractionDigits(2);

        BigDecimal desc60 = new BigDecimal("0.6");

        productPromotion.stream().forEach(p -> p.setPricePromotion(p.getPrice()
                .subtract(p.getPrice().multiply(desc60))));
        productPromotion.stream().forEach(p -> p.setMessage("O produto está em promoção com 60% de desconto."));

        return productPromotion;

    }

    @Override
    public List<ProductPromotionDTO> findBatchesFilteredByProductsTrash() {
        List<Batch> batches = batchRepository.findAll();
        List<Batch> batchesFiltered = filterDueDateInterval7(batches);
        List<ProductPromotionDTO> productPromotion = batchesFiltered.stream()
                .map(b -> b.toProductPromotionDTO(b.getProduct().getProductId(), b.getProduct().getName(),
                        b.getDueDate(), b.getProduct().getPrice(), null, " "))
                .collect(Collectors.toList());

        productPromotion.stream().forEach(p -> p.setMessage("O produto está com menos de 7 dias da data de vencimento, descarte."));

        return productPromotion;

    }
    
    //testado
//    @Override
//    public List<ProductPromotionDTO> findBatchesFilteredByDueDateAndPromotion(Integer maxIntervalDate, Integer minIntervalDate) {
//        List<Batch> batches = batchRepository.findAll();
//        List<Batch> batchesFiltered = filterNotExpiredProductsByDaysInterval(batches, maxIntervalDate, minIntervalDate);
//        List<ProductPromotionDTO> productPromotion = batchesFiltered.stream().map(b -> b.toProductPromotionDTO(b.getProduct().getProductId(), b.getProduct().getName(),
//                b.getDueDate(), b.getProduct().getPrice(), new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN), " ")).collect(Collectors.toList());
//
//        BigDecimal desc30 = new BigDecimal(0.30);
//        BigDecimal desc60 = new BigDecimal(0.60);
//
//        if (minIntervalDate >= 15 && maxIntervalDate <= 21) {
//            productPromotion.stream().forEach(p -> p.setPricePromotion(p.getPrice()
//                    .subtract(p.getPrice().multiply(desc30))));
//            productPromotion.stream().forEach(p -> p.setMessage("O produto está em promoção com 30% de desconto."));
//        } else if (minIntervalDate >= 07 && maxIntervalDate <= 15) {
//            productPromotion.stream().forEach(p -> p.setPricePromotion(p.getPrice()
//                    .subtract(p.getPrice().multiply(desc60))));
//            productPromotion.stream().forEach(p -> p.setMessage("O produto está em promoção com 60% de desconto."));
//        } else if (maxIntervalDate > 0 && maxIntervalDate < 7) {
//            productPromotion.stream().forEach(p -> p.setMessage
//                    ("O produto está com menos de 7 dias da data de vencimento, descarte."));
//        }
//        return productPromotion;
//    }

}



