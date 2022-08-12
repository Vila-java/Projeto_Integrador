package meli.freshfood.utils;

import meli.freshfood.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductUtils {

    public static Product newProduct(){
        Seller seller = SellerUtils.newSellerWithoutProducts();
        return new Product(1L, "Frango", "Descrição",
                StorageType.FRESH, new BigDecimal("22.4"), 4D, null,
                seller, null);
    }


    public static Product newProductWithParams(Long id, String name, String description, StorageType storageType, BigDecimal price){
        Seller seller = SellerUtils.newSellerWithoutProducts();
        return new Product(id, name, description, storageType, price, 4D, null, seller, null);
    }

    public static List<Product> productList(){
        List<Product> productList = new ArrayList<>();
        productList.add(newProductWithParams(1L, "Frango", "Frango ótimo", StorageType.FRESH, new BigDecimal(12)));
        productList.add(newProductWithParams(2L, "Carne", " Espetinho de carne", StorageType.FROZEN, new BigDecimal(2)));
        productList.add(newProductWithParams(3L, "Peixe", " File de peixe empanado", StorageType.REFRIGERATED, new BigDecimal(15)));

        return productList;
    }
}

