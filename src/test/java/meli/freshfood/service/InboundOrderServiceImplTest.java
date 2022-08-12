package meli.freshfood.service;

import meli.freshfood.dto.InboundOrderDTO;
import meli.freshfood.model.InboundOrder;
import meli.freshfood.model.Supervisor;
import meli.freshfood.model.Warehouse;
import meli.freshfood.model.Section;
import meli.freshfood.repository.InboundOrderRepository;
import meli.freshfood.utils.InboundOrderUtils;
import meli.freshfood.utils.SectionUtils;
import meli.freshfood.utils.SupervisorUtils;
import meli.freshfood.utils.WarehouseUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class InboundOrderServiceImplTest {

    @InjectMocks
    InboundOrderServiceImpl inboundOrderService;

    @Mock
    InboundOrderRepository inboundOrderRepository;

    @Mock
    WarehouseServiceImpl warehouseService;

    @Mock
    SectionServiceImpl sectionService;

    @Mock
    SupervisorServiceImpl supervisorService;

    @Mock
    BatchServiceImpl batchService;

    @Test
    @DisplayName("Cria novo pedido de compra")
    void createInboundOrder_whenNewInboundOrder() {
        BDDMockito.when(warehouseService.findById(Mockito.anyLong()))
                .thenReturn(WarehouseUtils.newWarehouse());

        Section section = SectionUtils.newSection();
        BDDMockito.when(sectionService.validatesSection(
                                    Mockito.any(InboundOrderDTO.class),
                                    Mockito.any(Warehouse.class))
                ).thenReturn(section);

        Supervisor supervisor = SupervisorUtils.newSupervisor();
        BDDMockito.when(supervisorService.validatesSupervisor(
                                    Mockito.any(InboundOrderDTO.class),
                                    Mockito.any(Warehouse.class))
                ).thenReturn(supervisor);

        BDDMockito.when(inboundOrderRepository.save(Mockito.any(InboundOrder.class)))
                .thenReturn(InboundOrderUtils.newInboundOrder(supervisor, section));

/*        BDDMockito.when(batchService.createBatches(Mockito.any(InboundOrderDTO.class), Mockito.any(Section.class), Mockito.any(InboundOrder.class)))
                .thenReturn(InboundOrderUtils.newInboundOrder(supervisor, section));*/

//        InboundOrder inboundOrderCreated = inboundOrderService.create(InboundOrderDTO inboundOrderDTO);
//
//
//
//        void create_returnStudent_whenNewStudent() {
//            StudentDTO newStudent = TestUtilsGenerator.getNewStudentWithOneSubject();
//
//            StudentDTO savedStudent = service.create(newStudent);
//
//            assertThat(savedStudent.getId()).isPositive();
//            assertThat(savedStudent.getStudentName()).isEqualTo(savedStudent.getStudentName());
//            verify(studentDAO, atLeastOnce()).save(newStudent);
//        }
//
//
//        BDDMockito.when(propRepo.getByName(ArgumentMatchers.anyString()))
//                .thenReturn(TestUtilsGenerator.getByNameWhenExist());
//        BDDMockito.when(districtRepo.getByName(ArgumentMatchers.anyString()))
//                .thenReturn(TestUtilsGenerator.createDistrict());
//
//        BigDecimal expected = TestUtilsGenerator.getTotalPriceByDistrict();
//
//        String propName = "Casa";
//        BigDecimal result = propService.calculatePropPriceByDistrict(propName);
//
//        assertThat(result).isEqualTo(expected);
    }
}