package meli.freshfood.utils;

import meli.freshfood.dto.ClientOrderDTO;
import meli.freshfood.model.Client;
import meli.freshfood.model.ClientOrder;
import meli.freshfood.model.ProductClientOrder;
import meli.freshfood.model.StatusOrder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ClientOrderUtils {

    public static ClientOrder newClientOrder(){
        Client client = ClientUtils.newClient();
        return new ClientOrder(null, LocalDate.now(), client, StatusOrder.PENDING);
    }

    public static ClientOrder newClientOrderWithProducts(){
        Set<ProductClientOrder> productClientOrder = ProductClientOrderUtils.newProductClientOrderList();
        Client client = ClientUtils.newClient();
        return new ClientOrder(productClientOrder, LocalDate.now(), client, StatusOrder.PENDING);

    }
    public static List<ClientOrder> newClientOrderList(){
        Client client = ClientUtils.newClient();
        List<ClientOrder> order = new ArrayList<>();
        order.add(new ClientOrder(null, LocalDate.now(), client, StatusOrder.PENDING));
        return order;
    }

    public static List<ClientOrderDTO> newClientOrderDTO() {
        Set<ProductClientOrder> productOrder = ProductClientOrderUtils.newProductOrderList();
        List<ClientOrderDTO> orderDTO = new ArrayList<>();
        orderDTO.add(new ClientOrderDTO(productOrder, LocalDate.now(), StatusOrder.APPROVED));
        return orderDTO;
    }
}
