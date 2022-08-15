package meli.freshfood.service;

import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Batch;
import meli.freshfood.model.Product;
import meli.freshfood.model.StorageType;
import meli.freshfood.repository.ProductRepository;
import meli.freshfood.utils.BatchUtils;
import meli.freshfood.utils.ProductUtils;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProductServiceImplTest {

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductRepository productRepo;

    @Test
    @DisplayName("Retorna o produto quando existir")
    void findById_returnProduct_whenProductIdExists() {
        Optional<Product> productMocked = Optional.of(ProductUtils.newProduct());

        BDDMockito.when(productRepo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(productMocked);

        Product product = productService.findById(ArgumentMatchers.anyLong());

        assertThat(product.getProductId()).isPositive();
        assertThat(product.getClass()).isEqualTo(Product.class);
    }

    @Test
    @DisplayName("Retorna exceção caso o Id do produto não exista")
    void returnNotFoundException_whenProductIdNotExists() {
        BDDMockito.when(productRepo.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new NotFoundException("O produto não foi encontrado!"));

        Exception exception = assertThrows(
                NotFoundException.class,
                () -> productService.findById(ArgumentMatchers.anyLong())
        );

        assertThat(exception.getMessage()).isEqualTo("O produto não foi encontrado!");
    }

    @Test
    @DisplayName("Retorna a lista de produtos quando existir")
    void findAllProducts_returnProductsList_whenProductListExists() {

        List<Product> productListMocked = ProductUtils.productList();

        BDDMockito.when(productRepo.findAll())
                .thenReturn(productListMocked);

        List<Product> productList = productService.findAll();
        assertThat(productList).isNotNull();
        assertThat(productList.size()).isEqualTo(productListMocked.size());
    }

    @Test
    @DisplayName("Retorna exceção caso o Id do produto não exista")
    void returnNotFoundException_whenFindAllProductsNotExists() {
        Exception exception = null;
        BDDMockito.when(productRepo.findAll())
                .thenReturn(new ArrayList<>());

        try {
            productService.findAll();
        }catch (Exception ex){
            exception = ex;
        }
        assertThat(exception.getMessage()).isEqualTo("A lista de produtos não foi encontrada!");
    }

    @Test
    @DisplayName("Retorna o produto por categoria")
    public void findProductByCategory() {
        List<Product> productListMocked = ProductUtils.productList();
        BDDMockito.when(productRepo.findByStorageType(StorageType.FRESH))
                .thenReturn(ProductUtils.productList());

        assertThat(productListMocked.get(0).getStorageType()).isEqualTo(StorageType.FRESH);
    }
}
