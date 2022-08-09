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

//    @Override
//    public List<ProductDTO> findAll() {
//        List<ProductDTO> productsList = productRepository.findAll(List<ProductDTO> productDTO);
//        if (productsList.isEmpty()) {
//            throw new NotFoundException("A lista de produtos não existe!");
//        }
//        return null;
//    }


    boolean productExists (PurchaseOrderDTO purchaseOrderDTO){
        Product product = new Product();

        purchaseOrderDTO.getProductsDTO().stream().map((p) ->
                findById(product.getProductId())
        );
        return true;
    }

    public boolean checkBatchAvailabe(List<ProductDTO> productsDTO) {
        productsDTO.stream().forEach((p) -> {
            Product product = findById(p.getProductId()).get();
            List<Batch> batches = batchService.filterNotExpiredProducts(product.getBatches());
            Integer totalCapacity = batches.stream()
                    .map((b) -> b.getCurrentQuantity())
                    .reduce(0, (b1, b2) -> b1 + b2);

            if(totalCapacity < p.getQuantity()) {
                // TODO: Ajustar exceção
                throw new NotFoundException("");
            }
        });
        return true;
    }


    public Batch updateStock(PurchaseOrderDTO purchaseOrderDTO){
        return null;

    }


}