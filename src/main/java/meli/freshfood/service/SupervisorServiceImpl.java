package meli.freshfood.service;

import meli.freshfood.dto.InboundOrderDTO;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Supervisor;
import meli.freshfood.model.Warehouse;
import meli.freshfood.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupervisorServiceImpl implements SupervisorService {

    @Autowired
    private SupervisorRepository supervisorRepository;

    @Override
    public Supervisor findById(Long id) {
        return supervisorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("O supervisor não foi encontrado!"));
    }

    @Override
    public Boolean supervisorExistsInWarehouse(Supervisor supervisor, Warehouse warehouse) {
        if (supervisor.getWarehouse().equals(warehouse)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Supervisor validatesSupervisor(InboundOrderDTO inboundOrderDTO, Warehouse warehouse) {
        Supervisor supervisor = findById(inboundOrderDTO.getSupervisorId());
        supervisorExistsInWarehouse(supervisor, warehouse);
        return supervisor;
    }

    @Override
    public Supervisor create (Supervisor supervisor) {
        return supervisorRepository.save(supervisor);
    }

    @Override
    public List<Supervisor> findAll() {
        List<Supervisor> listSupervisor = supervisorRepository.findAll();

        if (listSupervisor.isEmpty()){
            throw new NotFoundException("Lista de supervisor não encontrada!");
        }

        return listSupervisor;
    }

    @Override
    public Supervisor update(Supervisor supervisor) {
        return supervisorRepository.save(supervisor);
    }


}