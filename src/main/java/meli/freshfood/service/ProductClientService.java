package meli.freshfood.service;

import meli.freshfood.dto.ClientOrderDTO;
import meli.freshfood.dto.PurchaseOrderDTO;
import meli.freshfood.model.*;
import org.springframework.stereotype.Service;

@Service
public interface ProductClientService {
    ProductClientOrder create(ProductClientOrder productClientOrder);
}
