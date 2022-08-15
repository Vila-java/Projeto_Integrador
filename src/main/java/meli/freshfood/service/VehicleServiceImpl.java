package meli.freshfood.service;

import meli.freshfood.model.Vehicle;
import meli.freshfood.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle findByName(String name) {
        return vehicleRepository.findByName(name);
    }
}
