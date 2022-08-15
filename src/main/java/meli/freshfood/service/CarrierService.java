package meli.freshfood.service;

import meli.freshfood.dto.CarrierDTO;
import meli.freshfood.model.Carrier;
import meli.freshfood.model.Vehicle;


import java.util.List;

public interface CarrierService {

    CarrierDTO save(CarrierDTO carrierDTO);

    List<Carrier> findByVehicle(Vehicle vehicle);

    List<CarrierDTO> findaAll();

    CarrierDTO findById(Long id);

    CarrierDTO updateById(CarrierDTO carrierDTO);

    void deleteById(Long id);
}
