package meli.freshfood.utils;

import meli.freshfood.model.*;

import java.math.BigDecimal;

public class ProductUtils {

    public static Product newProduct(){
        Seller seller = SellerUtils.newSellerWithoutProducts();
        return new Product(1L, "Frango", "Descrição",
                StorageType.FRESH, new BigDecimal("22.4"), 4D, null,
                seller, null);
    }
}
