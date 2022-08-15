package meli.freshfood.utils;

import meli.freshfood.model.*;

import java.util.ArrayList;
import java.util.List;


public class SellerUtils {
    public static Seller newSellerWithoutProducts() {
        return new Seller(1L, "João", "Silva", null);
    }
}
