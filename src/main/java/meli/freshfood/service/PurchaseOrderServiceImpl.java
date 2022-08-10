package meli.freshfood.service;

import meli.freshfood.dto.ProductDTO;
import meli.freshfood.dto.PurchaseOrderDTO;
import meli.freshfood.model.Product;
import meli.freshfood.model.ProductPurchaseOrder;
import meli.freshfood.model.PurchaseOrder;
import meli.freshfood.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService{

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    ProductService productService;

    @Override
    public Double createPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) {
        productService.checkBatchAvailableList(purchaseOrderDTO.getProductsDTO());
        return null;
    }

    public PurchaseOrder addProduct(PurchaseOrder purchaseOrder, ProductDTO productDTO) {
        Product product = productService.findById(productDTO.getProductId()).get();
   //    ProductPurchaseOrder productPurchaseOrder = new ProductPurchaseOrder();
        return null;
    }

//    @Override
//    public PurchaseOrder getById(Long id){
//        return purchaseOrderRepository.findById(id).get();
//    }

//   @Override
//   public List<ProductDTO> productsDto listAll(){
//        return null;
//    }

    @Override
    public List<PurchaseOrder> findAllPurchaseOrder() {
        return purchaseOrderRepository.findAll();
    }

    @Override
    public PurchaseOrder statusPurchase(PurchaseOrderDTO purchaseOrderDTO, Long id) {
        Optional<PurchaseOrder> findShoppingCart = purchaseOrderRepository.findById(id);
        return null;
    }
}
