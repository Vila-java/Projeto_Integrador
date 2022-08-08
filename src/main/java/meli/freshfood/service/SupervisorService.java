package meli.freshfood.service;

import meli.freshfood.model.Supervisor;

import java.util.Optional;

public interface SupervisorService {

    Optional<Supervisor> findById(Long id);


}
