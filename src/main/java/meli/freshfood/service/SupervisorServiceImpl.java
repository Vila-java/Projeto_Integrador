package meli.freshfood.service;

import meli.freshfood.model.Supervisor;
import meli.freshfood.model.Warehouse;
import meli.freshfood.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupervisorServiceImpl implements SupervisorService {

    @Autowired
    private SupervisorRepository supervisorRepository;

    @Override
    public Optional<Supervisor> findById(Long id) {
        Optional<Supervisor> supervisor = supervisorRepository.findById(id);

        if (supervisor.isEmpty()) {
            // TODO: Alterar exceção
            throw new RuntimeException();
        }
        return supervisor;
    }

    @Override
    public Boolean supervisorExistsInWarehouse(Supervisor supervisor, Warehouse warehouse) {
        if (supervisor.getWarehouse().equals(warehouse)) {
            return true;
        } else {
            return false;
        }
    }
}
