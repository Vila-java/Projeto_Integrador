package meli.freshfood.service;

import meli.freshfood.dto.ProductReviewCreateDTO;
import meli.freshfood.dto.ProductReviewUpdateDTO;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Client;
import meli.freshfood.model.Product;
import meli.freshfood.model.ProductReview;
import meli.freshfood.repository.ProductRepository;
import meli.freshfood.repository.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    @Override
    public List<ProductReview> findAll() {
        return productReviewRepository.findAll();
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
