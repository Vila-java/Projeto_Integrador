package meli.freshfood.service;

import meli.freshfood.dto.PurchaseDeliveryDTO;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.*;
import meli.freshfood.repository.PurchaseDeliveryRepository;
import meli.freshfood.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseDeliveryServiceImpl implements PurchaseDeliveryService {

    @Autowired
    private PurchaseDeliveryRepository purchaseDeliveryRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    private CarrierService carrierService;


    @Override
    public PurchaseDelivery save(PurchaseDeliveryDTO purchaseDeliveryDTO) {
        PurchaseOrder purchaseOrder = purchaseOrderService.findbyId(purchaseDeliveryDTO.getPurchaseOrderId());
        VehiclesType vehiclesType = calculeteCapacity(purchaseDeliveryDTO.getQuantityOfBox());
        Vehicle vehicle = vehicleRepository.findByName(vehiclesType.getDescription());
        List<Carrier> validCarriers = carrierService.findByVehicle(vehicle);
        if (validCarriers == null || validCarriers.isEmpty()) {
            throw new NotFoundException("Não há motoristas disponíveis para entrega!");
        }
        Carrier carrier = validCarriers.get(0);
        PurchaseDelivery purchaseDelivery = PurchaseDelivery.builder().purchaseOrder(purchaseOrder)
                .carrier(carrier)
                .quantityOfBox(purchaseDeliveryDTO.getQuantityOfBox())
                .build();
        purchaseDelivery = purchaseDeliveryRepository.save(purchaseDelivery);

        return purchaseDelivery;
    }

    public VehiclesType calculeteCapacity(Integer quantityOfbox) {
        if (quantityOfbox <= 2) {
            return VehiclesType.MOTOCICLETA;
        } else if (quantityOfbox <= 10) {
            return VehiclesType.CARRO;
        }
        return VehiclesType.CAMINHAO;
    }
}


