package meli.freshfood.service;

import meli.freshfood.dto.SupervisorDetailsDTO;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.*;
import meli.freshfood.repository.SupervisorRepository;
import meli.freshfood.utils.SupervisorUtils;
import meli.freshfood.utils.WarehouseUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SupervisorServiceImplTest {

    @InjectMocks
    SupervisorServiceImpl supervisorServiceImpl;

    @Mock
    SupervisorRepository supervisorRepository;


    @Test
    @DisplayName("Retorna o supervisor quando ele existir")
    void findById_returnSupervisor_whenSupervisorIdExists() {
        Optional<Supervisor> supervisorMocked = Optional.of(SupervisorUtils.newSupervisor());

        BDDMockito.when(supervisorRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(supervisorMocked);

        Supervisor supervisor = supervisorServiceImpl.findById(ArgumentMatchers.anyLong());

        assertThat(supervisor.getSupersivorId ()).isPositive();
        assertThat(supervisor.getClass()).isEqualTo(Supervisor.class);
    }

    @Test
    @DisplayName("Retorna exceção caso o supervisor não exista")
    void returnNotFoundException_whenSupervisorIdNotExists(){
        BDDMockito.when(supervisorRepository.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new NotFoundException("O supervisor não foi encontrado!"));

        Exception exception = assertThrows(
                NotFoundException.class,
                () -> supervisorServiceImpl.findById(ArgumentMatchers.anyLong())
        );

        assertThat(exception.getMessage()).isEqualTo("O supervisor não foi encontrado!");
    }

    @Test
    @DisplayName("Retorna o supersivor caso ele exista no armazém")
    void supervisorExistsInWarehouse_returnTrueSupervisor_whenSupervisorExistsInWarehouse() {
        Supervisor supervisor = SupervisorUtils.newSupervisor();
        Warehouse warehouse = WarehouseUtils.newWarehouseWithSupervisor(supervisor);
        supervisor.setWarehouse(warehouse);

        Boolean supervisorExists = supervisorServiceImpl.supervisorExistsInWarehouse(supervisor, warehouse);

        assertThat(supervisorExists).isEqualTo(true);

    }


    @Test @DisplayName("Retorna exceção NotFoundException caso o supervisor não exista no armazém")
    void returnNotFoundException_whenSupervisorNotExistsInWharehouse() {
          Supervisor supervisor = SupervisorUtils.newSupervisor();
          Warehouse warehouse = WarehouseUtils.newWarehouse();

    }

    @Test
    @DisplayName("Retorna o supervisor salvo")
    void save_returnSupervisor() {
        Supervisor supervisorToSave = SupervisorUtils.newSupervisor();

        BDDMockito.when(supervisorRepository.save(ArgumentMatchers.any(Supervisor.class)))
                .thenReturn(supervisorToSave);

        Supervisor supervisor = supervisorServiceImpl.create(supervisorToSave);

        assertThat(supervisor.getSupersivorId()).isPositive();
        assertThat(supervisor.getClass()).isEqualTo(Supervisor.class);
    }

    @Test
    @DisplayName("Retorna uma lista de Supervisores quando ele existe no Warehouse informado.")
    void findAllSupervisores_returnWarehouseList_whenWerehouseListExists() {

        List<Supervisor> supervisorListMocked = SupervisorUtils.supervisorList();

        BDDMockito.when(supervisorRepository.findAll())
                .thenReturn(supervisorListMocked);

        List<SupervisorDetailsDTO> supervisorList = supervisorServiceImpl.findAllByWarehouseSupervisor("warehouseSupervisor");
        assertThat(supervisorList).isNotNull();
        assertThat(supervisorList.size()).isEqualTo(supervisorListMocked.size());
    }

}