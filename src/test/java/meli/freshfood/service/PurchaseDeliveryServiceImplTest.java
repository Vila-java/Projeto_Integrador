package meli.freshfood.service;

import meli.freshfood.dto.PurchaseDeliveryDTO;
import meli.freshfood.model.*;
import meli.freshfood.repository.PurchaseDeliveryRepository;
import meli.freshfood.utils.CarrierUtils;
import meli.freshfood.utils.PurchaseDeliveryUtils;
import meli.freshfood.utils.PurchaseOrderUtils;
import meli.freshfood.utils.VehiclesUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PurchaseDeliveryServiceImplTest {

    @InjectMocks
    PurchaseDeliveryServiceImpl purchaseDeliveryService;

    @Mock
    PurchaseDeliveryRepository purchaseDeliveryRepository;

    @Mock
    PurchaseOrderService purchaseOrderService;

    @Mock
    VehicleServiceImpl vehicleService;

    @Mock
    CarrierServiceImpl carrierService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_returnPurchaseDelivery_WhenNewPurchaseDelivery() {
        Optional<PurchaseDelivery> purchaseDeliveryMocked = Optional.of(PurchaseDeliveryUtils.newPurchaseDelivery());
        Vehicle vehicleMocked = VehiclesUtil.newVehicle();
        Optional<PurchaseOrder> purchaseOrderMocked = Optional.of(PurchaseOrderUtils.newPurchaseOrderUtils());
        List<Carrier> carrierListMocked = CarrierUtils.carrierList();

        BDDMockito.when(purchaseOrderService.findbyId(anyLong())).thenReturn(purchaseOrderMocked.get());
        BDDMockito.when(purchaseDeliveryRepository.save(ArgumentMatchers.any(PurchaseDelivery.class)))
                .thenReturn(PurchaseDeliveryUtils.newPurchaseDelivery());

        BDDMockito.when(carrierService.findByVehicle(ArgumentMatchers.any(Vehicle.class))).thenReturn(carrierListMocked);
        BDDMockito.when(vehicleService.findByName(anyString())).thenReturn(vehicleMocked);


        PurchaseDeliveryDTO purchaseDeliveryDTO = PurchaseDeliveryUtils.newPurchaseDeliveryDTO();
        PurchaseDelivery newPurchaseDelivery = purchaseDeliveryService.save(purchaseDeliveryDTO);

        assertThat(newPurchaseDelivery.getId()).isPositive();
        assertThat(newPurchaseDelivery.getCarrier()).isEqualTo(newPurchaseDelivery.getCarrier());

    }
}




