package meli.freshfood.service;

import meli.freshfood.dto.ProductDTO;
import meli.freshfood.dto.ProductDetailsDTO;
import meli.freshfood.dto.PurchaseOrderDTO;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.*;
import meli.freshfood.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Purchase order service.
 */
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService{

    /**
     * The Purchase order repository.
     */
    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    /**
     * The Product service.
     */
    @Autowired
    ProductService productService;

    /**
     * The Product purchase order service.
     */
    @Autowired
    ProductPurchaseOrderService productPurchaseOrderService;

    /**
     * The Batch service.
     */
    @Autowired
    BatchService batchService;

    /**
     * The Client service.
     */
    @Autowired
    ClientService clientService;


    @Override
    public Double create(PurchaseOrderDTO purchaseOrderDTO) {
        Client client = clientService.findById(purchaseOrderDTO.getBuyerId());
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findByClient(client);
        if(purchaseOrder == null) {
            purchaseOrder = new PurchaseOrder(purchaseOrderDTO, client);
            purchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        }
        PurchaseOrder finalPurchaseOrder = purchaseOrder;
        purchaseOrderDTO.getProducts().stream().forEach((productDTO) -> {
            batchService.updateStock(productDTO);
            addProductToPurchase(finalPurchaseOrder, productDTO);
        });

        BigDecimal totalPrice = productPurchaseOrderService.totalPriceAllProducts(purchaseOrder);
        return totalPrice.doubleValue();
    }

    @Override
    public void addProductToPurchase(PurchaseOrder purchaseOrder, ProductDTO productDTO) {
        Product product = productService.findById(productDTO.getProductId());
        ProductPurchaseOrder productPurchaseOrder = productPurchaseOrderService.findByPurchaseOrderAndProduct(purchaseOrder, product);
        if(productPurchaseOrder == null) {
            productPurchaseOrderService.create(product, purchaseOrder, productDTO.getQuantity());
        } else {
            Integer quantity = productDTO.getQuantity() + productPurchaseOrder.getProductQuantity();
            productPurchaseOrderService.update(productPurchaseOrder, quantity);
        }
    }

    @Override
    public List<ProductDetailsDTO> findAllProducts(Long purchaseOrderId) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(purchaseOrderId).get();
        return purchaseOrder.getProductPurchaseOrders()
                .stream()
                .map((productPurchase) -> {
                    Product product = productPurchase.getProduct();
                    return new ProductDetailsDTO(
                            product.getProductId(),
                            product.getName(),
                            productPurchase.getProductQuantity(),
                            product.getPrice()
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public void closePurchaseOrder(Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("O carrinho de compras não foi encontrado!"));
        productPurchaseOrderService.removeAllOrders(purchaseOrder);
        purchaseOrder.setOrderStatus(StatusPurchaseOrder.DISABLED);
        purchaseOrderRepository.save(purchaseOrder);
    }
}
