package meli.freshfood.service;
import meli.freshfood.model.ClientOrder;
import meli.freshfood.model.ProductClientOrder;
import meli.freshfood.repository.ProductClientOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductClientOrderServiceImpl implements ProductClientService {

    @Autowired
    private ProductClientOrderRepository productClientOrderRepository;

    @Override
    public ProductClientOrder create(ProductClientOrder productClientOrder) {
        return productClientOrderRepository.save(productClientOrder);
    }
}
