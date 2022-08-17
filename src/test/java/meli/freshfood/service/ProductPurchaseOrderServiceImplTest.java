package meli.freshfood.service;

import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Product;
import meli.freshfood.model.ProductPurchaseOrder;
import meli.freshfood.model.PurchaseOrder;
import meli.freshfood.repository.ProductPurchaseOrderRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import meli.freshfood.utils.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProductPurchaseOrderServiceImplTest {

    @InjectMocks
    ProductPurchaseOrderServiceImpl productPurchaseOrderService;

    @Mock
    ProductPurchaseOrderRepository productPurchaseOrderRepo;

    @Test
    void create_ShouldReturnAProductPurchaseOrder_IfAProductPurchaseOrderIsCreated() {
        BDDMockito.when(productPurchaseOrderRepo.save(Mockito.any(ProductPurchaseOrder.class)))
                .thenReturn(ProductPurchaseOrderUtils.newProductPurchaseOrder());

        ProductPurchaseOrder expected = ProductPurchaseOrderUtils.newProductPurchaseOrder();

        ProductPurchaseOrder productPurchaseOrder = productPurchaseOrderService.create(ProductUtils.newProduct(), PurchaseOrderUtils.newPurchaseOrderWithProductsUtils(), 1);

        assertThat(productPurchaseOrder.getId()).isEqualTo(expected.getId());
    }

    @Test
    void update_ShouldReturnAProductPurchaseOrder_IfTheProductPurchaseOrderIsUpdated() {
        BDDMockito.when(productPurchaseOrderRepo.save(Mockito.any(ProductPurchaseOrder.class)))
                .thenReturn(ProductPurchaseOrderUtils.newProductPurchaseOrder());

        ProductPurchaseOrder expected = ProductPurchaseOrderUtils.newProductPurchaseOrder();

        ProductPurchaseOrder productPurchaseOrder = productPurchaseOrderService.update(ProductPurchaseOrderUtils.newProductPurchaseOrder(), 1);

        assertThat(productPurchaseOrder.getId()).isEqualTo(expected.getId());
    }

    @Test
    @DisplayName("Retorna um produto da ordem de compra quando existir")
   void findById_returnBatch_whenProductPurchaseOrderIdExists() {
        Optional<ProductPurchaseOrder> productPurchaseOrderMocked = Optional.of(ProductPurchaseOrderUtils.newProductPurchaseOrder());

        BDDMockito.when(productPurchaseOrderRepo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(productPurchaseOrderMocked);

        ProductPurchaseOrder productPurchaseOrder = productPurchaseOrderService.findById(ArgumentMatchers.anyLong());

        assertThat(productPurchaseOrder.getId()).isPositive();
        assertThat(productPurchaseOrder.getClass()).isEqualTo(ProductPurchaseOrder.class);
    }

    @Test
    @DisplayName("Retorna uma exception caso o Id não exista")
    void returnNotFoundException_whenProductPurchaseIdNotExists() {

        BDDMockito.when(productPurchaseOrderRepo.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new NotFoundException("Não existe essa relação!"));

        Exception exception = assertThrows(
                NotFoundException.class,
                () -> productPurchaseOrderService.findById(ArgumentMatchers.anyLong())
        );
        assertThat(exception.getMessage()).isEqualTo("Não existe essa relação!");

    }

    @Test
    @DisplayName("Retorna o preço total de um carrinho de compra")
    void totalPriceAllProducts_ShouldReturnTotalPriceOfAllProductsInAPurchaseOrder() {
        PurchaseOrder purchaseOrder = PurchaseOrderUtils.newPurchaseOrderWithProductsUtils();
        BDDMockito.when(productPurchaseOrderRepo.findAllByPurchaseOrder(ArgumentMatchers.any(PurchaseOrder.class)))
                .thenReturn(ProductPurchaseOrderUtils.newProductPurchaseOrderList());

        BigDecimal productPurchaseOrder = productPurchaseOrderService.totalPriceAllProducts(purchaseOrder);

        BigDecimal expected = new BigDecimal("44.8");

        assertThat(productPurchaseOrder).isEqualTo(expected);

    }

    @Test
    void findByPurchaseOrderAndProduct() {
        BDDMockito.when(productPurchaseOrderRepo.findByPurchaseOrderAndProduct(Mockito.any(PurchaseOrder.class), Mockito.any(Product.class)))
                .thenReturn(ProductPurchaseOrderUtils.newProductPurchaseOrder());

        ProductPurchaseOrder productPurchaseOrder = productPurchaseOrderService.findByPurchaseOrderAndProduct(PurchaseOrderUtils.newPurchaseOrderWithProductsUtils(), ProductUtils.newProduct());
        ProductPurchaseOrder expected = ProductPurchaseOrderUtils.newProductPurchaseOrder();
        assertThat(expected).isEqualTo(expected);
    }

    @Test
    void removeAllOrdersShouldRemoveAllOrders() {
        BDDMockito.when(productPurchaseOrderRepo.findAllByPurchaseOrder(Mockito.any(PurchaseOrder.class)))
                .thenReturn(ProductPurchaseOrderUtils.newProductPurchaseOrderList());
        doNothing().when(productPurchaseOrderRepo).deleteAll();

        productPurchaseOrderService.removeAllOrders(PurchaseOrderUtils.newPurchaseOrderWithProductsUtils());
    }

}
