package meli.freshfood.utils;

import meli.freshfood.model.ClientOrder;
import meli.freshfood.model.Product;
import meli.freshfood.model.ProductClientOrder;

import java.util.HashSet;
import java.util.Set;

public class ProductClientOrderUtils {
         public static ProductClientOrder newProductClientOrder() {
            Product product = ProductUtils.newProduct();
            ClientOrder clientOrder = ClientOrderUtils.newClientOrder();
            return new ProductClientOrder(2, product, clientOrder);
        }
        public static Set<ProductClientOrder> newProductClientOrderList() {
            Product product = ProductUtils.newProduct();
            ClientOrder clientOrder = ClientOrderUtils.newClientOrder();
            Set<ProductClientOrder> productClientOrder = new HashSet<>();
            productClientOrder.add(new ProductClientOrder(2, product, clientOrder));
            return productClientOrder;
        }
        public static Set<ProductClientOrder> newProductOrderList() {
            Product product = ProductUtils.newProduct();
            ClientOrder order = ClientOrderUtils.newClientOrder();
            Set<ProductClientOrder> productOrder = new HashSet<>();
            productOrder.add(new ProductClientOrder(2, product, order));
            return productOrder;
        }
}
