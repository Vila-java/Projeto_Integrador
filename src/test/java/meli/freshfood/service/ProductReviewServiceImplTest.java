package meli.freshfood.service;

import meli.freshfood.dto.ProductReviewCreateDTO;
import meli.freshfood.dto.ProductReviewUpdateDTO;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Client;
import meli.freshfood.model.Product;
import meli.freshfood.model.ProductPurchaseOrder;
import meli.freshfood.model.ProductReview;
import meli.freshfood.repository.ProductPurchaseOrderRepository;
import meli.freshfood.repository.ProductReviewRepository;
import meli.freshfood.utils.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProductReviewServiceImplTest {
    @InjectMocks
    ProductReviewServiceImpl productReviewService;

    @Mock
    ProductReviewRepository productReviewRepository;

    @Mock
    ProductServiceImpl productService;

    @Mock
    ClientServiceImpl clientService;

    @Test
    @DisplayName("Retorna lista de avaliações")
    void findAll_returnReviewList_whenReviewsExists() {
        List<ProductReview> productReviewsMock = new ArrayList<>();
        productReviewsMock.add(ProductReviewUtils.newProductReview());
        productReviewsMock.add(ProductReviewUtils.newProductReview());

        BDDMockito.when(productReviewRepository.findAll()).thenReturn(productReviewsMock);

        List<ProductReview> productReviews = productReviewService.findAll();

        assertThat(productReviews.getClass()).isEqualTo(ArrayList.class);
        assertThat(productReviews.size()).isEqualTo(productReviewsMock.size());
    }

    @Test
    @DisplayName("Retorna lista vazia")
    void findAll_returnEmptyList_whenReviewsNotExists() {
        BDDMockito.when(productReviewRepository.findAll()).thenReturn(new ArrayList<>());

        List<ProductReview> productReviews = productReviewService.findAll();

        assertThat(productReviews.getClass()).isEqualTo(ArrayList.class);
        assertThat(productReviews.size()).isZero();
    }

    @Test
    @DisplayName("Retorna avaliação")
    void findById_returnReview_whenReviewIdExists() {
        ProductReview productReviewMock = ProductReviewUtils.newProductReview();
        BDDMockito.when(productReviewRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(productReviewMock));

        ProductReview productReview = productReviewService.findById(ArgumentMatchers.anyLong());

        assertThat(productReview.getClass()).isEqualTo(ProductReview.class);
        assertThat(productReview.getProductReviewId()).isPositive();
    }

    @Test
    @DisplayName("Retorna NotFoundException quando avaliação não existe")
    void findById_returnNotFoundException_whenReviewIdNotExists() {
        BDDMockito.when(productReviewRepository.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new NotFoundException("Avaliação não encontrada!"));

        Exception exception = assertThrows(
                NotFoundException.class,
                () -> productReviewService.findById(ArgumentMatchers.anyLong())
        );

        assertThat(exception.getMessage()).isEqualTo("Avaliação não encontrada!");
    }

    @Test
    void delete_returnVoid_whenReviewIdExists() {
        ProductReview productReviewMock = ProductReviewUtils.newProductReview();
        BDDMockito.when(productReviewRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(productReviewMock));

        productReviewService.delete(productReviewMock.getProductReviewId());

        verify(productReviewRepository, times(1)).delete(productReviewMock);
    }

    @Test
    void delete_returnNotFoundException_whenReviewIdNotExists() {
        BDDMockito.when(productReviewRepository.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new NotFoundException("Avaliação não encontrada!"));

        Exception exception = assertThrows(
                NotFoundException.class,
                () -> productReviewService.delete(ArgumentMatchers.anyLong())
        );

        assertThat(exception.getMessage()).isEqualTo("Avaliação não encontrada!");
    }

    @Test
    void create_returnProductReviewObject_whenClientIdAndProductExists() {
        BDDMockito.when(productService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(ProductUtils.newProduct());

        BDDMockito.when(clientService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(ClientUtils.newClient());

        ProductReview productReviewMock = ProductReviewUtils.newProductReview();
        BDDMockito.when(productReviewRepository.save(any(ProductReview.class)))
                .thenReturn(productReviewMock);

        ProductReviewCreateDTO productReviewCreateDTO = ProductReviewUtils.newProductReviewCreateDTO();
        ProductReview productReview = productReviewService.create(productReviewCreateDTO);

        assertThat(productReview.getProductReviewId()).isPositive();
        assertThat(productReview.getClass()).isEqualTo(ProductReview.class);
    }

    @Test
    void create_returnNotFoundException_whenProductIdNotExists() {
        BDDMockito.when(productService.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new NotFoundException("O produto não foi encontrado!"));

        ProductReviewCreateDTO productReviewCreateDTO = ProductReviewUtils.newProductReviewCreateDTO();
        Exception exception = assertThrows(
                NotFoundException.class,
                () -> productReviewService.create(productReviewCreateDTO)
        );

        assertThat(exception.getMessage()).isEqualTo("O produto não foi encontrado!");
    }

    @Test
    void create_returnNotFoundException_whenClientIdNotExists() {
        BDDMockito.when(productService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(ProductUtils.newProduct());

        BDDMockito.when(clientService.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new NotFoundException("O cliente não foi encontrado!"));

        ProductReviewCreateDTO productReviewCreateDTO = ProductReviewUtils.newProductReviewCreateDTO();
        Exception exception = assertThrows(
                NotFoundException.class,
                () -> productReviewService.create(productReviewCreateDTO)
        );

        assertThat(exception.getMessage()).isEqualTo("O cliente não foi encontrado!");
    }

    @Test
    void update_returnProductReviewObject_whenProductReviewIdExists() {
        ProductReview productReviewMock = ProductReviewUtils.newProductReview();
        BDDMockito.when(productReviewRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(productReviewMock));

        String newDescription = "Nova descrição";
        Integer newGrade = 5;
        productReviewMock.setDescription(newDescription);
        productReviewMock.setGrade(newGrade);
        BDDMockito.when(productReviewRepository.save(any(ProductReview.class)))
                .thenReturn(productReviewMock);

        ProductReviewUpdateDTO productReviewUpdateDTO = ProductReviewUtils.newProductReviewUpdateDTO(newDescription, newGrade);
        ProductReview productReview = productReviewService.update(anyLong(), productReviewUpdateDTO);

        assertThat(productReview.getProductReviewId()).isPositive();
        assertThat(productReview.getDescription()).isEqualTo(newDescription);
        assertThat(productReview.getGrade()).isEqualTo(newGrade);
    }

    @Test
    void update_returnNotFoundException_whenProductReviewIdNotExists() {
        BDDMockito.when(productReviewRepository.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new NotFoundException("Avaliação não encontrada!"));

        String newDescription = "Nova descrição";
        Integer newGrade = 5;
        ProductReviewUpdateDTO productReviewUpdateDTO = ProductReviewUtils.newProductReviewUpdateDTO(newDescription, newGrade);

        Exception exception = assertThrows(
                NotFoundException.class,
                () -> productReviewService.update(anyLong(), productReviewUpdateDTO)
        );

        assertThat(exception.getMessage()).isEqualTo("Avaliação não encontrada!");
    }

    @Test
    void filterByProductId_returnReviewsOfProduct_whenReviewsWithProductExists() {
        Client client = ClientUtils.newClient();
        Product product1 = ProductUtils.newProduct();
        Product product2 = ProductUtils.newProduct();

        List<ProductReview> productReviewsMock = new ArrayList<>();
        productReviewsMock.add(ProductReviewUtils.newProductReviewWithProductAndClient(product1, client));
        productReviewsMock.add(ProductReviewUtils.newProductReviewWithProductAndClient(product1, client));
        productReviewsMock.add(ProductReviewUtils.newProductReviewWithProductAndClient(product2, client));

        BDDMockito.when(productService.findById(anyLong())).thenReturn(product1);

        List<ProductReview> productReviews = productReviewService.filterByProductId(productReviewsMock, product1.getProductId());

        assertThat(productReviews.getClass()).isEqualTo(ArrayList.class);
        Integer numberOfReviewsProduct1 = 2;
        assertThat(productReviews.size()).isEqualTo(numberOfReviewsProduct1);
    }

    @Test
    void filterByClientId_returnReviewsOfClient_whenReviewsWithClientExists() {
        Client client1 = ClientUtils.newClient();
        Client client2 = ClientUtils.newClient();
        Product product = ProductUtils.newProduct();

        List<ProductReview> productReviewsMock = new ArrayList<>();
        productReviewsMock.add(ProductReviewUtils.newProductReviewWithProductAndClient(product, client1));
        productReviewsMock.add(ProductReviewUtils.newProductReviewWithProductAndClient(product, client1));
        productReviewsMock.add(ProductReviewUtils.newProductReviewWithProductAndClient(product, client2));

        BDDMockito.when(clientService.findById(anyLong())).thenReturn(client1);

        List<ProductReview> productReviews = productReviewService.filterByClientId(productReviewsMock, client1.getClientId());

        assertThat(productReviews.getClass()).isEqualTo(ArrayList.class);
        Integer numberOfReviewsClient1 = 2;
        assertThat(productReviews.size()).isEqualTo(numberOfReviewsClient1);
    }

    @Test
    void findAllWithFilter_returnAllReviews_whenNoArgs() {
        Client client1 = ClientUtils.newClient();
        Client client2 = ClientUtils.newClient();
        Product product = ProductUtils.newProduct();

        List<ProductReview> productReviewsMock = new ArrayList<>();
        productReviewsMock.add(ProductReviewUtils.newProductReviewWithProductAndClient(product, client1));
        productReviewsMock.add(ProductReviewUtils.newProductReviewWithProductAndClient(product, client1));
        productReviewsMock.add(ProductReviewUtils.newProductReviewWithProductAndClient(product, client2));

        BDDMockito.when(productReviewRepository.findAll()).thenReturn(productReviewsMock);

        List<ProductReview> productReviews = productReviewService.findAllWithFilter(null, null);

        assertThat(productReviews.getClass()).isEqualTo(ArrayList.class);
        assertThat(productReviews.size()).isEqualTo(productReviewsMock.size());
    }

    @Test
    void findAllWithFilter_returnReviewsWithProductId_whenSendProductId() {
        Client client1 = ClientUtils.newClient();
        Client client2 = ClientUtils.newClient();
        Product product1 = ProductUtils.newProduct();
        Product product2 = ProductUtils.newProduct();

        List<ProductReview> productReviewsMock = new ArrayList<>();
        productReviewsMock.add(ProductReviewUtils.newProductReviewWithProductAndClient(product1, client1));
        productReviewsMock.add(ProductReviewUtils.newProductReviewWithProductAndClient(product2, client1));
        productReviewsMock.add(ProductReviewUtils.newProductReviewWithProductAndClient(product1, client2));

        BDDMockito.when(productReviewRepository.findAll()).thenReturn(productReviewsMock);
        BDDMockito.when(productService.findById(anyLong())).thenReturn(product1);

        List<ProductReview> productReviews = productReviewService.findAllWithFilter(product1.getProductId(), null);

        assertThat(productReviews.getClass()).isEqualTo(ArrayList.class);
        Integer numberOfReviewsProduct1 = 2;
        assertThat(productReviews.size()).isEqualTo(numberOfReviewsProduct1);
    }

    @Test
    void findAllWithFilter_returnReviewsWithClientId_whenSendClientId() {
        Client client1 = ClientUtils.newClient();
        Client client2 = ClientUtils.newClient();
        Product product1 = ProductUtils.newProduct();
        Product product2 = ProductUtils.newProduct();

        List<ProductReview> productReviewsMock = new ArrayList<>();
        productReviewsMock.add(ProductReviewUtils.newProductReviewWithProductAndClient(product1, client1));
        productReviewsMock.add(ProductReviewUtils.newProductReviewWithProductAndClient(product2, client1));
        productReviewsMock.add(ProductReviewUtils.newProductReviewWithProductAndClient(product1, client2));

        BDDMockito.when(productReviewRepository.findAll()).thenReturn(productReviewsMock);
        BDDMockito.when(clientService.findById(anyLong())).thenReturn(client2);

        List<ProductReview> productReviews = productReviewService.findAllWithFilter(null, client2.getClientId());

        assertThat(productReviews.getClass()).isEqualTo(ArrayList.class);
        Integer numberOfReviewsClient2 = 1;
        assertThat(productReviews.size()).isEqualTo(numberOfReviewsClient2);
    }


}