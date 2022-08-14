package meli.freshfood.service;

import meli.freshfood.dto.ClientOrderDTO;
import meli.freshfood.model.ClientOrder;
import meli.freshfood.model.StatusOrder;
import meli.freshfood.repository.ClientOrderRepository;
import meli.freshfood.utils.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ClientOrderServiceImplTest {

    @InjectMocks
    ClientOrderServiceImpl clientOrderService;

    @Mock
    ClientOrderRepository clientOrderRepository;

    @Mock
    ProductClientService productClientService;

    @Mock
    ClientService clientService;


    @Test
    void findById_ShouldReturnAnClientOrderWhenClientOrderExists() {
        Optional<ClientOrder> clientOrderMocked = Optional.of(ClientOrderUtils.newClientOrder());

        BDDMockito.when(clientOrderRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(clientOrderMocked);


        ClientOrder clientOrder = clientOrderService.findById(1L);

        ClientOrder expected = ClientOrderUtils.newClientOrder();

        assertThat(clientOrder.getId()).isEqualTo(expected.getId());

    }
    @Test
    void saveOrder_ShouldReturnClientOrderAndSaveClientOrderInDatabase() {
        BDDMockito.when(clientOrderRepository.save(ArgumentMatchers.any()))
                .thenReturn(ClientOrderUtils.newClientOrder());
        BDDMockito.when(productClientService.create(ArgumentMatchers.any()))
                .thenReturn(ProductClientOrderUtils.newProductClientOrder());

        ClientOrder clientOrder = clientOrderService.saveOrder(PurchaseOrderUtils.newPurchaseOrderWithProductsUtilsDisabled());

        ClientOrder expected = ClientOrderUtils.newClientOrderWithProducts();

        assertThat(clientOrder.getId()).isEqualTo(expected.getId());
    }

    @Test
    void getAllOrders_ShouldReturnAListWithOrders() {
        BDDMockito.when(clientOrderRepository.findByClient(ArgumentMatchers.any()))
                .thenReturn(ClientOrderUtils.newClientOrderList());
        BDDMockito.when(clientService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(ClientUtils.newClient());

        List<ClientOrderDTO> order = clientOrderService.getAllOrders(1L);

        List<ClientOrderDTO> expected = ClientOrderUtils.newClientOrderDTO();

        assertThat(order.get(0).getDate()).isEqualTo(expected.get(0).getDate());
    }

    @Test
    void changeOrderStatus_ShouldChangeTheStatusOfTheOrderAndReturnAMessageOfSuccess() {
        Optional<ClientOrder> orderMocked = Optional.of(ClientOrderUtils.newClientOrder());
        BDDMockito.when(clientOrderRepository.save(ArgumentMatchers.any()))
                .thenReturn(ClientOrderUtils.newClientOrder());
        BDDMockito.when(clientOrderRepository.findById(ArgumentMatchers.any()))
                .thenReturn(orderMocked);

        String order = clientOrderService.changeOrderStatus(1L, "CANCELED");

        String expected = "Status do pedido foi alterado com sucesso!";

        assertThat(order).isEqualTo(expected);

    }

    @Test
    void changeOrderStatus_ShouldReturnAnIllegalArgumentsException() {
        Optional<ClientOrder> orderMocked = Optional.of(ClientOrderUtils.newClientOrder());
        BDDMockito.when(clientOrderRepository.save(ArgumentMatchers.any()))
                .thenReturn(ClientOrderUtils.newClientOrder());
        BDDMockito.when(clientOrderRepository.findById(ArgumentMatchers.any()))
                .thenReturn(orderMocked);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> clientOrderService.
                changeOrderStatus(1L, "CANCELADO"));
        assertThat(ex.getMessage()).isEqualTo("Entrada invalida! Possiveis valores: " +
                Arrays.toString(StatusOrder.values()));

    }


}