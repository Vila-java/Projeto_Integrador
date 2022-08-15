package meli.freshfood.utils;

import meli.freshfood.model.*;

public class ClientUtils {

    public static Client newClient(){
        return new Client(1L, "Jennifer", "Richmond", null);
    }
}