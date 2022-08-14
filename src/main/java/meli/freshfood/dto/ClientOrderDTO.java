package meli.freshfood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import meli.freshfood.model.ProductClientOrder;
import meli.freshfood.model.ProductPurchaseOrder;
import meli.freshfood.model.StatusOrder;

import java.time.LocalDate;
import java.util.Set;
@Getter
@AllArgsConstructor
public class ClientOrderDTO {

    private Set<ProductClientOrder> productClientOrder;
    private LocalDate date;
    private StatusOrder orderStatus;
}
