package meli.freshfood.service;

import meli.freshfood.exception.InternalServerErrorException;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Product;
import meli.freshfood.model.Section;
import meli.freshfood.model.StorageType;
import meli.freshfood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("O produto não foi encontrado!"));
    }

    @Override
    public List<Product> findAll() {
        List<Product> listProducts = productRepository.findAll();

        if(listProducts.isEmpty())  {
            throw new NotFoundException("A lista de produtos não foi encontrada!");
        }
        return listProducts;
    }

    @Override
    public List<Product> findProductByCategory(String storageType) {
        StorageType storageTypeObj = StorageType.parseToStorage(storageType);
        List<Product> listProducts = productRepository.findByStorageType(storageTypeObj);
        if(listProducts.isEmpty())  {
            throw new NotFoundException("A lista de produtos não foi encontrada!");
        }
        return listProducts;
    }

    @Override
    public Boolean checkProductStorageIsEqualSectionStorage(Product product, Section section) {
        if (section.getStorageType().equals(product.getStorageType())) {
            return true;
        } else {
            throw new InternalServerErrorException("O setor e o produto não têm o mesmo tipo de armazenamento!");
        }
    }

    @Override
    public Product findProductByDueDatePromotion() {
        return null;
    }
}

//    @Override
//    public Product findProductByDueDatePromotion() {
//        return null;
//    }

//    @Override
//    public List<Product> filterProductsByIntervalLess21DaysDueDate(List<Product> products) {
//        List<Product> products = productRepository.findAll();
//
//        switch (intervalDate) {
//            case 1:
//                promotion 30%;
//                break;
//            case 2:
//                promotion 60%;
//                break;
//            case 3:
//                trash;
//                break;
//            default:
//                String message = "Produto dentro do prazo de validade.";
//
//        }
//    }
//
//    @Override
//    public BigDecimal totalPriceAllProductsPromotion(PurchaseOrder purchaseOrder) {
//        List<ProductPurchaseOrder> productPurchaseOrders = productPurchaseOrderRepository.findAllByPurchaseOrder(purchaseOrder);
//        BigDecimal totalPricePromotion = productPurchaseOrders.stream().map((po) -> {
//            return po.getProduct().getPrice().multiply(new BigDecimal(po.getProductQuantity()));
//        }).reduce(new BigDecimal(0), (po1, po2) -> po1.add(po2));
//        return totalPricePromotion;
//    }
