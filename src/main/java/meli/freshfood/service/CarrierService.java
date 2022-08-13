package meli.freshfood.service;

import meli.freshfood.dto.CarrierDTO;


import java.util.List;

public interface CarrierService {

    CarrierDTO save(CarrierDTO carrierDTO);

    List<CarrierDTO> findaAll();

    CarrierDTO findById(Long id);

    CarrierDTO updateById(CarrierDTO tutorial);

    void deleteById(Long id);
}
