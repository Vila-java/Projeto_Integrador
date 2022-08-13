package meli.freshfood.service;

import meli.freshfood.model.Carrier;

import java.util.List;

public interface CarrierService {

    Carrier save(Carrier carrier);

    List<Carrier> findaAll();

    Carrier findById(Long id);

    Carrier updateById(Carrier tutorial);

    void deleteById(Long id);
}
