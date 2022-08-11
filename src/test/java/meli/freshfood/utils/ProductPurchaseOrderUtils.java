package meli.freshfood.utils;

import meli.freshfood.model.Product;
import meli.freshfood.model.ProductPurchaseOrder;
import meli.freshfood.model.PurchaseOrder;
import meli.freshfood.model.Client;
import meli.freshfood.model.Seller;


public class ProductPurchaseOrderUtils {
    public static ProductPurchaseOrder newProductPurchaseOrder() {
        Seller seller = SellerUtils.newSellerWithoutProducts();
        Product product = ProductUtils.newProduct();
        PurchaseOrder purchaseOrder = PurchaseOrderUtils.newPurchaseOrderUtils();
        ProductPurchaseOrder productPurchaseOrder = new ProductPurchaseOrder(1L, product, purchaseOrder, 1);

        return productPurchaseOrder;
    }

}
