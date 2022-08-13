package meli.freshfood.service;

import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Carrier;
import meli.freshfood.repository.CarrierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrierServiceImpl implements CarrierService {

    @Autowired
    private CarrierRepository carrierRepository;

   /* @Autowired
    private ModelMapper modelMapper;*/

    @Override
    public Carrier save(Carrier carrier) {
        return carrierRepository.save(carrier);
    }

    @Override
    public List<Carrier> findaAll() {
        return carrierRepository.findAll();
    }

    @Override
    public Carrier findById(Long id) {
        return carrierRepository.findById(id).orElseThrow(() -> new NotFoundException("Carrier not found!"));
    }

    @Override
    public Carrier updateById(Carrier carrier) {
        findById(carrier.getId());
        return carrierRepository.save(carrier);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        carrierRepository.deleteById(id);

    }
}
