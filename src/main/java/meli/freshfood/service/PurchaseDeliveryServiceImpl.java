package meli.freshfood.service;

import meli.freshfood.dto.PurchaseDeliveryDTO;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.*;
import meli.freshfood.repository.PurchaseDeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseDeliveryServiceImpl implements PurchaseDeliveryService {

    @Autowired
    private PurchaseDeliveryRepository purchaseDeliveryRepository;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    private CarrierService carrierService;


    @Override
    public PurchaseDelivery save(PurchaseDeliveryDTO purchaseDeliveryDTO) {
        PurchaseOrder purchaseOrder = purchaseOrderService.findbyId(purchaseDeliveryDTO.getPurchaseOrderId());
        List<Carrier> validCarriers = getCarriersByQuantityOfBox(purchaseDeliveryDTO.getQuantityOfBox());

        Carrier carrier = validCarriers.get(0);
        PurchaseDelivery purchaseDelivery = PurchaseDelivery.builder().purchaseOrder(purchaseOrder)
                .carrier(carrier)
                .quantityOfBox(purchaseDeliveryDTO.getQuantityOfBox())
                .build();
        purchaseDelivery = purchaseDeliveryRepository.save(purchaseDelivery);

        return purchaseDelivery;
    }

    public List<Carrier> getCarriersByQuantityOfBox(Integer quantityOfBox) {
        Vehicle vehicle = null;
        List<Carrier> validCarriers = new ArrayList<>();

        if (quantityOfBox <= 2) {
            vehicle = vehicleService.findByName(VehiclesType.MOTOCICLETA.getDescription());
            validCarriers = carrierService.findByVehicle(vehicle);
        }

        if (quantityOfBox <= 10 && validCarriers.isEmpty()) {
            vehicle = vehicleService.findByName(VehiclesType.CARRO.getDescription());
            validCarriers = carrierService.findByVehicle(vehicle);
        }

        if (quantityOfBox <= 30 && validCarriers.isEmpty())  {
            vehicle = vehicleService.findByName(VehiclesType.CAMINHAO.getDescription());
            validCarriers = carrierService.findByVehicle(vehicle);
        }

        if (validCarriers == null || validCarriers.isEmpty()) {
            throw new NotFoundException("Não há motoristas disponíveis para entrega!");
        }
        return validCarriers;

    }

}