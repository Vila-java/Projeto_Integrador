package meli.freshfood.service;

import meli.freshfood.exception.BadRequestException;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.*;
import meli.freshfood.repository.ProductRepository;

import meli.freshfood.utils.ProductUtils;
import meli.freshfood.utils.SectionUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.mockito.quality.Strictness;

import java.util.*;

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

    @Test
    @DisplayName("Retorna exceção caso não haja produtos em uma categoria")
    void returnNotFoundException_whenFindAllClientsNotExists() {
        Exception exception = null;

        try {
            productService.findProductByCategory("FRESH");
        }catch (Exception ex) {
            exception = ex;
        }

        assertThat(exception.getMessage()).isEqualTo("A lista de produtos não foi encontrada!");
    }

    @Test
    @DisplayName("Verifica se o tipo de armazenamento do produto é igual ao armazenamento do setor")
    void returnTrue_ifProductStorage_isequalTo_SectionStorage(){

        Boolean p1 = productService.checkProductStorageIsEqualSectionStorage(ProductUtils.newProduct(), SectionUtils.newSection());
        assertThat(p1).isEqualTo(true);
    }

    @Test
    @DisplayName("Retorna exceção caso não haja produtos em uma categoria")
    void returnBadRequestException_whenProductStorage_isequalTo_SectionStorage() {

        Exception exception = assertThrows(
                BadRequestException.class,
                () -> productService.checkProductStorageIsEqualSectionStorage(ProductUtils.newProduct(), SectionUtils.newSection2())
        );

        assertThat(exception.getMessage()).isEqualTo("O setor e o produto não têm o mesmo tipo de armazenamento!");
    }

}
