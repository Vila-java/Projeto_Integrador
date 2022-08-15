package meli.freshfood.service;

import meli.freshfood.dto.BatchDTO;
import meli.freshfood.dto.InboundOrderDTO;
import meli.freshfood.dto.SectionDTO;
import meli.freshfood.exception.BadRequestException;
import meli.freshfood.exception.NotFoundException;
import meli.freshfood.model.*;
import meli.freshfood.repository.InboundOrderRepository;
import meli.freshfood.utils.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void createInboundOrder_returnBatchList_whenNewInboundOrder() {
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

        BDDMockito.when(batchService.createBatches(Mockito.any(InboundOrderDTO.class), Mockito.any(Section.class), Mockito.any(InboundOrder.class)))
                .thenReturn(BatchUtils.newBatchDTOList());

        SectionDTO sectionDTO = SectionUtils.newSectionDTO(section, section.getWarehouse());
        List<BatchDTO> batchesMockedDTO = BatchUtils.newBatchDTOList();
        InboundOrderDTO inboundOrderDTOMocked = InboundOrderUtils.newInboundOrderDTO(supervisor, sectionDTO, batchesMockedDTO);

        List<BatchDTO> batches = inboundOrderService.create(inboundOrderDTOMocked);

        //TODO: Adicionar mais casos de teste
        assertThat(batches).isNotEmpty()
                            .isInstanceOf(List.class);

        assertThat(batches.size()).isEqualTo(batchesMockedDTO.size());
    }


    @Test
    @DisplayName("Retorna exceção de não encontrado quando o armazém não existe")
    void createInboundOrder_returnNotFoundException_whenWarehouseNotExists() {
        BDDMockito.when(warehouseService.findById(Mockito.anyLong()))
                .thenThrow(new NotFoundException("O armazém não foi encontrado!"));

        Section section = SectionUtils.newSection();
        Supervisor supervisor = SupervisorUtils.newSupervisor();
        SectionDTO sectionDTO = SectionUtils.newSectionDTO(section, section.getWarehouse());
        List<BatchDTO> batchesMockedDTO = BatchUtils.newBatchDTOList();
        InboundOrderDTO inboundOrderDTOMocked = InboundOrderUtils.newInboundOrderDTO(supervisor, sectionDTO, batchesMockedDTO);

        Exception exception = assertThrows(
                NotFoundException.class,
                () -> inboundOrderService.create(inboundOrderDTOMocked)
        );

        assertThat(exception.getMessage()).isEqualTo("O armazém não foi encontrado!");
    }

    @Test
    @DisplayName("Retorna exceção de não encontrado quando setor não existe")
    void createInboundOrder_returnNotFoundException_whenSectionNotExists() {
        BDDMockito.when(warehouseService.findById(Mockito.anyLong()))
                .thenReturn(WarehouseUtils.newWarehouse());

        BDDMockito.when(sectionService.validatesSection(Mockito.any(InboundOrderDTO.class), Mockito.any(Warehouse.class)))
                .thenThrow(new NotFoundException("O setor não foi encontrado!"));

        Section section = SectionUtils.newSection();
        Supervisor supervisor = SupervisorUtils.newSupervisor();
        SectionDTO sectionDTO = SectionUtils.newSectionDTO(section, section.getWarehouse());
        List<BatchDTO> batchesMockedDTO = BatchUtils.newBatchDTOList();
        InboundOrderDTO inboundOrderDTOMocked = InboundOrderUtils.newInboundOrderDTO(supervisor, sectionDTO, batchesMockedDTO);

        Exception exception = assertThrows(
                NotFoundException.class,
                () -> inboundOrderService.create(inboundOrderDTOMocked)
        );

        assertThat(exception.getMessage()).isEqualTo("O setor não foi encontrado!");
    }

    @Test
    @DisplayName("Retorna exceção de bad request quando o usuário faz uma entrada maior que a capacidade do setor")
    void createInboundOrder_returnBadRequestException_whenSectionNotSupportStock() {
        BDDMockito.when(warehouseService.findById(Mockito.anyLong()))
                .thenReturn(WarehouseUtils.newWarehouse());

        BDDMockito.when(sectionService.validatesSection(Mockito.any(InboundOrderDTO.class), Mockito.any(Warehouse.class)))
                .thenThrow(new BadRequestException("Capacidade de armazenamento excedida!"));

        Section section = SectionUtils.newSection();
        Supervisor supervisor = SupervisorUtils.newSupervisor();
        SectionDTO sectionDTO = SectionUtils.newSectionDTO(section, section.getWarehouse());
        List<BatchDTO> batchesMockedDTO = BatchUtils.newBatchDTOList();
        InboundOrderDTO inboundOrderDTOMocked = InboundOrderUtils.newInboundOrderDTO(supervisor, sectionDTO, batchesMockedDTO);

        Exception exception = assertThrows(
                BadRequestException.class,
                () -> inboundOrderService.create(inboundOrderDTOMocked)
        );

        assertThat(exception.getMessage()).isEqualTo("Capacidade de armazenamento excedida!");
    }

    @Test
    @DisplayName("Retorna exceção de bad request quando o usuário envia setor e armazém incompatíveis")
    void createInboundOrder_returnBadRequestException_whenSectionDontBelongsToWarehouse() {
        BDDMockito.when(warehouseService.findById(Mockito.anyLong()))
                .thenReturn(WarehouseUtils.newWarehouse());

        BDDMockito.when(sectionService.validatesSection(Mockito.any(InboundOrderDTO.class), Mockito.any(Warehouse.class)))
                .thenThrow(new BadRequestException("O setor não pertence ao armazém!"));

        Section section = SectionUtils.newSection();
        Supervisor supervisor = SupervisorUtils.newSupervisor();
        SectionDTO sectionDTO = SectionUtils.newSectionDTO(section, section.getWarehouse());
        List<BatchDTO> batchesMockedDTO = BatchUtils.newBatchDTOList();
        InboundOrderDTO inboundOrderDTOMocked = InboundOrderUtils.newInboundOrderDTO(supervisor, sectionDTO, batchesMockedDTO);

        Exception exception = assertThrows(
                BadRequestException.class,
                () -> inboundOrderService.create(inboundOrderDTOMocked)
        );

        assertThat(exception.getMessage()).isEqualTo("O setor não pertence ao armazém!");
    }

    @Test
    @DisplayName("Retorna exceção de não encotrado quando o supervisor não existe")
    void createInboundOrder_returnNotFoundException_whenSupervisorNotExists() {
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
        ).thenThrow(new NotFoundException("O supervisor não foi encontrado!"));

        SectionDTO sectionDTO = SectionUtils.newSectionDTO(section, section.getWarehouse());
        List<BatchDTO> batchesMockedDTO = BatchUtils.newBatchDTOList();
        InboundOrderDTO inboundOrderDTOMocked = InboundOrderUtils.newInboundOrderDTO(supervisor, sectionDTO, batchesMockedDTO);

        Exception exception = assertThrows(
                NotFoundException.class,
                () -> inboundOrderService.create(inboundOrderDTOMocked)
        );

        assertThat(exception.getMessage()).isEqualTo("O supervisor não foi encontrado!");
    }

    @Test
    @DisplayName("Retorna exceção de bad request quando o supervisor não existe no armazém passado")
    void createInboundOrder_returnBadRequest_whenSupervisorNotExistsInWarehouse() {
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
        ).thenThrow(new BadRequestException("O supervisor não pertence ao armazém"));

        SectionDTO sectionDTO = SectionUtils.newSectionDTO(section, section.getWarehouse());
        List<BatchDTO> batchesMockedDTO = BatchUtils.newBatchDTOList();
        InboundOrderDTO inboundOrderDTOMocked = InboundOrderUtils.newInboundOrderDTO(supervisor, sectionDTO, batchesMockedDTO);

        Exception exception = assertThrows(
                BadRequestException.class,
                () -> inboundOrderService.create(inboundOrderDTOMocked)
        );

        assertThat(exception.getMessage()).isEqualTo("O supervisor não pertence ao armazém");
    }

    @Test
    @DisplayName("Retorna exceção de não encontrado quando o produto passado não existe")
    void createInboundOrder_returnNotFoundException_whenProductNotExists() {
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

        BDDMockito.when(batchService.createBatches(Mockito.any(InboundOrderDTO.class), Mockito.any(Section.class), Mockito.any(InboundOrder.class)))
                .thenThrow(new NotFoundException("O produto não foi encontrado!"));

        SectionDTO sectionDTO = SectionUtils.newSectionDTO(section, section.getWarehouse());
        List<BatchDTO> batchesMockedDTO = BatchUtils.newBatchDTOList();
        InboundOrderDTO inboundOrderDTOMocked = InboundOrderUtils.newInboundOrderDTO(supervisor, sectionDTO, batchesMockedDTO);

        Exception exception = assertThrows(
                NotFoundException.class,
                () -> inboundOrderService.create(inboundOrderDTOMocked)
        );

        assertThat(exception.getMessage()).isEqualTo("O produto não foi encontrado!");
    }

    @Test
    @DisplayName("Retorna exceção quando tipo de produto e tipo de setor são diferentes")
    void createInboundOrder_returnBadRequestException_whenProductStorageDiffSectionStorage() {
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

        BDDMockito.when(batchService.createBatches(Mockito.any(InboundOrderDTO.class), Mockito.any(Section.class), Mockito.any(InboundOrder.class)))
                .thenThrow(new BadRequestException("O setor e o produto não têm o mesmo tipo de armazenamento!"));

        SectionDTO sectionDTO = SectionUtils.newSectionDTO(section, section.getWarehouse());
        List<BatchDTO> batchesMockedDTO = BatchUtils.newBatchDTOList();
        InboundOrderDTO inboundOrderDTOMocked = InboundOrderUtils.newInboundOrderDTO(supervisor, sectionDTO, batchesMockedDTO);

        Exception exception = assertThrows(
                BadRequestException.class,
                () -> inboundOrderService.create(inboundOrderDTOMocked)
        );

        assertThat(exception.getMessage()).isEqualTo("O setor e o produto não têm o mesmo tipo de armazenamento!");
    }

    @Test
    @DisplayName("Retorna a ordem de entrada quando existir")
    void findById_returnInboundOrder_whenInboundOrderIdExists() {
        Section section = SectionUtils.newSection();
        Supervisor supervisor = SupervisorUtils.newSupervisor();
        Optional<InboundOrder> inboundOrderMocked = Optional.of(InboundOrderUtils.newInboundOrder(supervisor, section));

        BDDMockito.when(inboundOrderRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(inboundOrderMocked);

        InboundOrder inboundOrder = inboundOrderService.findById(ArgumentMatchers.anyLong());

        assertThat(inboundOrder.getOrderNumber()).isPositive();
        assertThat(inboundOrder.getClass()).isEqualTo(InboundOrder.class);
    }

    @Test
    @DisplayName("Retorna exceção quando a ordem de entrada não existe")
    void returnNotFoundException_whenInboundOrderNotExists() {

            BDDMockito.when(inboundOrderRepository.findById(ArgumentMatchers.anyLong()))
                    .thenThrow(new NotFoundException("A Ordem de entrada não foi encontrado!"));

            Exception exception = assertThrows(
                    NotFoundException.class,
                    () -> inboundOrderService.findById(ArgumentMatchers.anyLong())
            );

            assertThat(exception.getMessage()).isEqualTo("A Ordem de entrada não foi encontrado!");
    }

}