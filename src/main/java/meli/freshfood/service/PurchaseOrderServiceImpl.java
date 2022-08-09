package meli.freshfood.service;

import meli.freshfood.dto.ProductDTO;
import meli.freshfood.model.Product;
import meli.freshfood.model.PurchaseOrder;
import meli.freshfood.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService{

   /* @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public PurchaseOrder getById(Long id){
        return purchaseOrderRepository.findById(id).get();
    }

   @Override
   public List<ProductDTO> productsDto listAll(){
        return null;
    }*/


}
