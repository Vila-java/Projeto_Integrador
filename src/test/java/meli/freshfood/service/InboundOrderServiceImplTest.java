package meli.freshfood.service;

import meli.freshfood.dto.InboundOrderDTO;
import meli.freshfood.model.InboundOrder;
import meli.freshfood.repository.InboundOrderRepository;
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

    @Test
    @DisplayName("Cria novo pedido de compra")
    void createInboundOrder_whenNewInboundOrder() {
        InboundOrder inboundOrder = new InboundOrder();
        BDDMockito.when(inboundOrderRepository.save(Mockito.any(InboundOrder.class)))
                .thenReturn(inboundOrder);

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