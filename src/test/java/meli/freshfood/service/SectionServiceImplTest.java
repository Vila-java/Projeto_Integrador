package meli.freshfood.service;

import meli.freshfood.dto.BatchDTO;
import meli.freshfood.dto.InboundOrderDTO;
import meli.freshfood.dto.SectionDTO;
import meli.freshfood.exception.BadRequestException;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.Section;
import meli.freshfood.model.Supervisor;
import meli.freshfood.model.Warehouse;
import meli.freshfood.repository.SectionRepository;
import meli.freshfood.utils.*;
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
class SectionServiceImplTest {

    @InjectMocks
    SectionServiceImpl sectionService;

    @Mock
    SectionRepository sectionRepository;

    @Test
    @DisplayName("Retorna o setor quando existir")
    void findById_returnSection_whenSectionIdExists() {
        Optional<Section> sectionMocked = Optional.of(SectionUtils.newSection());

        BDDMockito.when(sectionRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(sectionMocked);

        Section section  = sectionService.findById(ArgumentMatchers.anyLong());

        assertThat(section.getSectionId()).isPositive();
        assertThat(section.getClass()).isEqualTo(Section.class);
    }

    @Test
    @DisplayName("Retorna exceção caso o Id do setor não exista")
    void returnNotFoundException_whenSectionIdNotExists() {
        BDDMockito.when(sectionRepository.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new NotFoundException("O setor não foi encontrado!"));

        Exception exception = assertThrows(
                NotFoundException.class,
                () -> sectionService.findById(ArgumentMatchers.anyLong())
        );

        assertThat(exception.getMessage()).isEqualTo("O setor não foi encontrado!");
    }

    @Test
    @DisplayName("Verifica se o setor pertence ao armazém")
    void returnTrue_ifSectorBelongsToWarehouse(){
        Warehouse warehouse = WarehouseUtils.newWarehouseWithSection();
        Section section = warehouse.getSections().get(0);
        section.setWarehouse(warehouse);

        Boolean check = sectionService.checkSectionBelongsToWarehouse(section, warehouse);
        assertThat(check).isEqualTo(true);
    }

    @Test
    @DisplayName("Retorna exceção caso o setor não pertença ao armazém")
    void returnBadRequestException_whenSectorDoesntBelongToWarehouse() {

        Exception exception = assertThrows(
                BadRequestException.class,
                () -> sectionService.checkSectionBelongsToWarehouse(SectionUtils.newSection(), WarehouseUtils.newWarehouse())
        );

        assertThat(exception.getMessage()).isEqualTo("O setor não pertence ao armazém!");
    }
}
