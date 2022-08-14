package meli.freshfood.utils;

import meli.freshfood.model.*;

import java.util.ArrayList;
import java.util.List;

public class ClientUtils {

    public static Client newClient(){
        return new Client(1L, "Jennifer", "Richmond", null);
    }

    public static Client newClientWithParams(Long clientId, String firstName, String lastName, PurchaseOrder purchaseOrder){
        return new Client(clientId, firstName, lastName, purchaseOrder);
    }

    public static List<Client> clientList(){
        List<Client> clientList = new ArrayList<>();
        clientList.add(newClientWithParams(1L, "Bianca", "Schmitt", null));
        clientList.add(newClientWithParams(2L, "Maria", "Schmitt", null));
        clientList.add(newClientWithParams(3L, "Edésio", "Schmitt", null));

        return clientList;
    }
}