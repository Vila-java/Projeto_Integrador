package meli.freshfood.service;

import meli.freshfood.dto.*;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Client;
import meli.freshfood.model.Product;
import meli.freshfood.model.ProductReview;
import meli.freshfood.repository.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {
    @Autowired
    private ProductReviewRepository productReviewRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientService clientService;

    @Override
    public ProductReview findById(Long productReviewId) {
        return productReviewRepository.findById(productReviewId)
                    .orElseThrow(() -> new NotFoundException("Avaliação não encontrada!"));
    }

    public List<ProductReview> findAll() {
        return productReviewRepository.findAll();
    }

    public List<ProductReview> filterByProductId(List<ProductReview> productReviews, Long productId) {
        Product product = productService.findById(productId);
        return productReviews.stream().filter((p) -> p.getProduct().equals(product))
                                .collect(Collectors.toList());
    }

    public List<ProductReview> filterByClientId(List<ProductReview> productReviews, Long clientId) {
        Client client = clientService.findById(clientId);
        return productReviews.stream().filter((p) -> p.getClient().equals(client))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductReview> findAllWithFilter(Long productId, Long clientId) {
        List<ProductReview> productReviews = productReviewRepository.findAll();
        if(productId != null) {
            productReviews = filterByProductId(productReviews, productId);
        }
        if(clientId != null) {
            productReviews = filterByClientId(productReviews, clientId);
        }
        return productReviews;
    }

    @Override
    public ProductReviewDTO productReviewOfProduct(Long productId) {
        Product product = productService.findById(productId);
        List<ProductReview> productReviews = productReviewRepository.findAllByProduct(product);
        if (productReviews.isEmpty()) {
            throw new NotFoundException("Não foram encontradas avaliações para esse produto!");
        } else {
            Double gradeAverage = productReviews.stream().mapToInt(ProductReview::getGrade).average().getAsDouble();
            List<String> descriptions = productReviews.stream().map(ProductReview::getDescription)
                                                .collect(Collectors.toList());
            return new ProductReviewDTO(productId, gradeAverage, product.getProductReviews().size(), descriptions);
        }
    }

    @Override
    public List<ProductReviewProductDTO> reviewsByProduct() {
        List<ProductReview> productReviews = findAll();
        Map<Product, Double> collect = productReviews.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getProduct(),
                        Collectors.averagingInt(p -> p.getGrade())
                ));

        List<ProductReviewProductDTO> productReviewProductDTOS = new ArrayList<>();
        collect.forEach((k, v) ->
                productReviewProductDTOS.add(
                        new ProductReviewProductDTO(k.getProductId(),
                                v, k.getProductReviews().size()
                        )
                )
        );
        return productReviewProductDTOS;
    }

    @Override
    public List<ProductReviewClientDTO> clientReviews(Long clientId) {
        Client client = clientService.findById(clientId);
        List<ProductReview> productReviews = productReviewRepository.findAllByClient(client);
        if (productReviews.isEmpty()) {
            throw new NotFoundException("O usuário ainda não realizou avaliações!");
        } else {
            List<ProductReviewClientDTO> productReviewClientDTOS = productReviews.stream()
                    .map((p) -> new ProductReviewClientDTO(p.getDescription(), p.getGrade(), p.getProduct().getProductId()))
                    .collect(Collectors.toList());
            return productReviewClientDTOS;
        }
    }

    @Override
    public ProductReview create(ProductReviewCreateDTO ProductReviewCreateDTO) {
        Product product = productService.findById(ProductReviewCreateDTO.getProductId());
        Client client = clientService.findById(ProductReviewCreateDTO.getClientId());
        ProductReview productReview = new ProductReview(ProductReviewCreateDTO, client, product);
        return productReviewRepository.save(productReview);
    }

    @Override
    public ProductReview update(Long productReviewId, ProductReviewUpdateDTO productReviewUpdateDTO) {
        ProductReview productReview = findById(productReviewId);
        productReview.setDescription(productReviewUpdateDTO.getDescription());
        productReview.setGrade(productReviewUpdateDTO.getGrade());
        productReview.setUpdatedAt(LocalDate.now());
        return productReviewRepository.save(productReview);
    }

    @Override
    public void delete(Long productReviewId) {
        ProductReview productReview = findById(productReviewId);
        productReviewRepository.delete(productReview);
    }

}
