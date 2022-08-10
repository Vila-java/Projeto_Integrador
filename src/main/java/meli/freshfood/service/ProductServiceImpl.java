package meli.freshfood.service;

import meli.freshfood.dto.ProductDTO;
import meli.freshfood.dto.PurchaseOrderDTO;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Batch;
import meli.freshfood.model.Product;
import meli.freshfood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BatchService batchService;

    @Override
    public Optional<Product> findById(Long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new NotFoundException("O produto não foi encontrado!");
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    boolean productExists(PurchaseOrderDTO purchaseOrderDTO) {
        Product product = new Product();

        purchaseOrderDTO.getProductsDTO().stream().map((p) ->
                findById(product.getProductId())
        );
        return true;
    }

    @Override
    public boolean checkBatchAvailable(ProductDTO productDTO) {
        Product product = findById(productDTO.getProductId()).get();
        Integer totalCapacityAvailable = batchService.totalAvailableBatchesCapacity(product.getBatches());

        if (totalCapacityAvailable < productDTO.getQuantity()) {
            // TODO: Ajustar exceção
            throw new NotFoundException("");
        }
        return true;
    }


    @Override
    public void checkBatchAvailableList(List<ProductDTO> productsDTO) {
        productsDTO.stream().forEach((p) -> {
            checkBatchAvailable(p);
        });
    }


    public boolean updateStock(PurchaseOrderDTO purchaseOrderDTO) {


//        purchaseOrderDTO.getProductsDTO().stream().forEach((pDTO) -> {
//            checkBatchAvailable(pDTO);
//            Product product = productRepository.findById(pDTO.getProductId()).get();
//            Integer productQuantity = pDTO.getQuantity();
//
//            List<Batch> batches = product.getBatches();
//
//            for(batches : b)
//
//
//            product.getBatches().forEach((b) -> {
//                productQuantity -= 1;
//            });
//
////            product.getBatches().stream().forEach((b) -> {
////                b.setCurrentQuantity(b.getCurrentQuantity() - p.getQuantity());
////                productQuantity -= b.getCurrentQuantity();
////            });
//        });
//
       return true;

    }
}