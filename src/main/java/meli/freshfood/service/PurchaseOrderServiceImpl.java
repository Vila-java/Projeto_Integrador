package meli.freshfood.service;

import meli.freshfood.dto.ProductDTO;
import meli.freshfood.dto.PurchaseOrderDTO;
import meli.freshfood.model.Client;
import meli.freshfood.model.Product;
import meli.freshfood.model.PurchaseOrder;
import meli.freshfood.model.StatusPurchaseOrder;
import meli.freshfood.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService{

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    ProductService productService;

    @Autowired
    ProductPurchaseOrderService productPurchaseOrderService;

    @Autowired
    BatchService batchService;

    @Autowired
    ClientService clientService;


    @Override
    public Double create(PurchaseOrderDTO purchaseOrderDTO) {
        Client client = clientService.findById(purchaseOrderDTO.getBuyerId());
        PurchaseOrder purchaseOrder = new PurchaseOrder(purchaseOrderDTO, client);
        purchaseOrderDTO.getProductsDTO().stream().forEach((productDTO) -> {
            batchService.updateStock(productDTO);
            addProductToPurchase(purchaseOrder, productDTO);
        });

        BigDecimal totalPrice = productPurchaseOrderService.totalPriceAllProducts(purchaseOrder);
        return totalPrice.doubleValue();
    }

    @Override
    public void addProductToPurchase(PurchaseOrder purchaseOrder, ProductDTO productDTO) {
        Product product = productService.findById(productDTO.getProductId());
        productPurchaseOrderService.create(product, purchaseOrder, productDTO.getQuantity());
    }

    @Override
    public List<Product> findAllProducts(Long purchaseOrderId) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(purchaseOrderId).get();
        return purchaseOrder.getProductPurchaseOrders()
                .stream()
                .map((productPurchase) -> productPurchase.getProduct())
                .collect(Collectors.toList());
    }

    @Override
    public void closePurchaseOrder(Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id).get();
        purchaseOrder.setOrderStatus(StatusPurchaseOrder.DISABLED);
        purchaseOrderRepository.save(purchaseOrder);
    }
}
