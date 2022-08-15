package meli.freshfood.service;

import meli.freshfood.dto.InboundOrderDTO;
import meli.freshfood.dto.SupervisorDetailsDTO;
import meli.freshfood.exception.BadRequestException;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Supervisor;
import meli.freshfood.model.Warehouse;
import meli.freshfood.repository.SupervisorRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;

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
            throw new BadRequestException("O supervisor não pertence ao armazém");
        }
    }

    @Override
    public Supervisor validatesSupervisor(InboundOrderDTO inboundOrderDTO, Warehouse warehouse) {
        Supervisor supervisor = findById(inboundOrderDTO.getSupervisorId());
        supervisorExistsInWarehouse(supervisor, warehouse);
        return supervisor;
    }

    //Método para criar um novo supervisor.
    @Override
    public Supervisor create (Supervisor supervisor) {
        return supervisorRepository.save(supervisor);
    }

    //Método para buscar todos os supervisores.
    @Override
    public List<Supervisor> findAll() {
        List<Supervisor> listSupervisor = supervisorRepository.findAll();

        if (listSupervisor.isEmpty()){
            throw new NotFoundException("Lista de supervisor não encontrada!");
        }

        return listSupervisor;
    }

    //Método para buscar um superviror por ID.
    @Override
    public Supervisor updateById(Supervisor supervisor) {
        return supervisorRepository.save(supervisor);
    }

    //Método para deletar um supervisor por ID.
    @Override
    public void deleteById(Long supersivorId) {
        findById(supersivorId);
        supervisorRepository.deleteById(supersivorId);
    }

    //Método para filtrar uma lista de supervisor por Warehouse (Ex: SP01)
    @Override
    public List<SupervisorDetailsDTO> findAllByWarehouseSupervisor(String warehouseSupervisor) {
        List<Supervisor> supervisorList = supervisorRepository.findAll();

        return supervisorList.stream()
                .filter(w -> w.getWarehouseSupervisor().equalsIgnoreCase(warehouseSupervisor))
                .map(SupervisorDetailsDTO::new)
                .collect(Collectors.toList());
    }
}