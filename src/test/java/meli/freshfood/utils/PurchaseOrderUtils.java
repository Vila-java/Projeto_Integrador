package meli.freshfood.utils;

import meli.freshfood.model.Client;
import meli.freshfood.model.ProductPurchaseOrder;
import meli.freshfood.model.PurchaseOrder;
import meli.freshfood.model.StatusPurchaseOrder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PurchaseOrderUtils {
    public static PurchaseOrder newPurchaseOrderUtils(){
        Client client = ClientUtils.newClient();
        return new PurchaseOrder(1L, LocalDate.of(2022, 01, 1),
                StatusPurchaseOrder.ACTIVATED, null, client);
    }

    public static PurchaseOrder newPurchaseOrderWithProductsUtils(){
        Client client = ClientUtils.newClient();

        Set<ProductPurchaseOrder> productPurchaseOrder = new HashSet<>();
        productPurchaseOrder.add(ProductPurchaseOrderUtils.newProductPurchaseOrder());
        productPurchaseOrder.add(ProductPurchaseOrderUtils.newProductPurchaseOrder());

        return new PurchaseOrder(1L, LocalDate.of(2022, 01, 1),
                StatusPurchaseOrder.ACTIVATED, productPurchaseOrder, client);
    }
}
