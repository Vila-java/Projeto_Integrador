package meli.freshfood.service;

import meli.freshfood.exception.InternalServerErrorException;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Warehouse;
import meli.freshfood.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public Optional<Warehouse> findById(Long id) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);

        if (warehouse.isEmpty()) {
            throw new NotFoundException("O armazém não foi encontrado!");
        }
        return warehouse;
    }
}






