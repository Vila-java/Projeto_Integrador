package meli.freshfood.service;

import meli.freshfood.client.ViaCepClient;
import meli.freshfood.dto.CarrierDTO;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Carrier;
import meli.freshfood.model.Vehicle;
import meli.freshfood.repository.CarrierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrierServiceImpl implements CarrierService {

    @Autowired
    private CarrierRepository carrierRepository;

    @Autowired
    private ModelMapper modelMapper;


    //método que cadastra um Carrier
    @Override
    public CarrierDTO save(CarrierDTO carrierDTO) {
        Carrier carrier = modelMapper.map(carrierDTO, Carrier.class);
        carrier = carrierRepository.save(carrier);
        carrierDTO.setId(carrier.getId());
        return carrierDTO;
    }

    @Override
    public List<Carrier> findByVehicle(Vehicle vehicle) {
        return carrierRepository.findByVehicle(vehicle);
    }

    //método que retorna todos os Carriers já ordenados por ordem alfabética
    @Override
    public List<CarrierDTO> findaAll() {
        List<Carrier> carriersList = carrierRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
        return modelMapper.map(carriersList, List.class);
    }

    //método que retorna um Carrier buscando pelo ID
    @Override
    public CarrierDTO findById(Long id) {
        Carrier carrier = carrierRepository.findById(id).orElseThrow(() -> new NotFoundException("Carrier not found!"));
        CarrierDTO dto = modelMapper.map(carrier, CarrierDTO.class);
        return dto;
    }

    //método que atualiza um Carrier
    @Override
    public CarrierDTO updateById(CarrierDTO carrierDTO) {
        findById(carrierDTO.getId());
        Carrier carrierToSave = modelMapper.map(carrierDTO, Carrier.class);
        Carrier carrier = carrierRepository.save(carrierToSave);
        return modelMapper.map(carrier, CarrierDTO.class);
    }

    //método que deleta um Carrier
    @Override
    public void deleteById(Long id) {
        findById(id);
        carrierRepository.deleteById(id);
    }
}