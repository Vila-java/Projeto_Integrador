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
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;

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

        assertThat(batches).isNotEmpty()
                            .isInstanceOf(List.class);

        assertThat(batches.size()).isEqualTo(batchesMockedDTO.size());
    }


    @Test
    @DisplayName("Retorna exce????o de n??o encontrado quando o armaz??m n??o existe")
    void createInboundOrder_returnNotFoundException_whenWarehouseNotExists() {
        BDDMockito.when(warehouseService.findById(Mockito.anyLong()))
                .thenThrow(new NotFoundException("O armaz??m n??o foi encontrado!"));

        Section section = SectionUtils.newSection();
        Supervisor supervisor = SupervisorUtils.newSupervisor();
        SectionDTO sectionDTO = SectionUtils.newSectionDTO(section, section.getWarehouse());
        List<BatchDTO> batchesMockedDTO = BatchUtils.newBatchDTOList();
        InboundOrderDTO inboundOrderDTOMocked = InboundOrderUtils.newInboundOrderDTO(supervisor, sectionDTO, batchesMockedDTO);

        Exception exception = assertThrows(
                NotFoundException.class,
                () -> inboundOrderService.create(inboundOrderDTOMocked)
        );

        assertThat(exception.getMessage()).isEqualTo("O armaz??m n??o foi encontrado!");
    }

    @Test
    @DisplayName("Retorna exce????o de n??o encontrado quando setor n??o existe")
    void createInboundOrder_returnNotFoundException_whenSectionNotExists() {
        BDDMockito.when(warehouseService.findById(Mockito.anyLong()))
                .thenReturn(WarehouseUtils.newWarehouse());

        BDDMockito.when(sectionService.validatesSection(Mockito.any(InboundOrderDTO.class), Mockito.any(Warehouse.class)))
                .thenThrow(new NotFoundException("O setor n??o foi encontrado!"));

        Section section = SectionUtils.newSection();
        Supervisor supervisor = SupervisorUtils.newSupervisor();
        SectionDTO sectionDTO = SectionUtils.newSectionDTO(section, section.getWarehouse());
        List<BatchDTO> batchesMockedDTO = BatchUtils.newBatchDTOList();
        InboundOrderDTO inboundOrderDTOMocked = InboundOrderUtils.newInboundOrderDTO(supervisor, sectionDTO, batchesMockedDTO);

        Exception exception = assertThrows(
                NotFoundException.class,
                () -> inboundOrderService.create(inboundOrderDTOMocked)
        );

        assertThat(exception.getMessage()).isEqualTo("O setor n??o foi encontrado!");
    }

    @Test
    @DisplayName("Retorna exce????o de bad request quando o usu??rio faz uma entrada maior que a capacidade do setor")
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
    @DisplayName("Retorna exce????o de bad request quando o usu??rio envia setor e armaz??m incompat??veis")
    void createInboundOrder_returnBadRequestException_whenSectionDontBelongsToWarehouse() {
        BDDMockito.when(warehouseService.findById(Mockito.anyLong()))
                .thenReturn(WarehouseUtils.newWarehouse());

        BDDMockito.when(sectionService.validatesSection(Mockito.any(InboundOrderDTO.class), Mockito.any(Warehouse.class)))
                .thenThrow(new BadRequestException("O setor n??o pertence ao armaz??m!"));

        Section section = SectionUtils.newSection();
        Supervisor supervisor = SupervisorUtils.newSupervisor();
        SectionDTO sectionDTO = SectionUtils.newSectionDTO(section, section.getWarehouse());
        List<BatchDTO> batchesMockedDTO = BatchUtils.newBatchDTOList();
        InboundOrderDTO inboundOrderDTOMocked = InboundOrderUtils.newInboundOrderDTO(supervisor, sectionDTO, batchesMockedDTO);

        Exception exception = assertThrows(
                BadRequestException.class,
                () -> inboundOrderService.create(inboundOrderDTOMocked)
        );

        assertThat(exception.getMessage()).isEqualTo("O setor n??o pertence ao armaz??m!");
    }

    @Test
    @DisplayName("Retorna exce????o de n??o encotrado quando o supervisor n??o existe")
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
        ).thenThrow(new NotFoundException("O supervisor n??o foi encontrado!"));

        SectionDTO sectionDTO = SectionUtils.newSectionDTO(section, section.getWarehouse());
        List<BatchDTO> batchesMockedDTO = BatchUtils.newBatchDTOList();
        InboundOrderDTO inboundOrderDTOMocked = InboundOrderUtils.newInboundOrderDTO(supervisor, sectionDTO, batchesMockedDTO);

        Exception exception = assertThrows(
                NotFoundException.class,
                () -> inboundOrderService.create(inboundOrderDTOMocked)
        );

        assertThat(exception.getMessage()).isEqualTo("O supervisor n??o foi encontrado!");
    }

    @Test
    @DisplayName("Retorna exce????o de bad request quando o supervisor n??o existe no armaz??m passado")
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
        ).thenThrow(new BadRequestException("O supervisor n??o pertence ao armaz??m"));

        SectionDTO sectionDTO = SectionUtils.newSectionDTO(section, section.getWarehouse());
        List<BatchDTO> batchesMockedDTO = BatchUtils.newBatchDTOList();
        InboundOrderDTO inboundOrderDTOMocked = InboundOrderUtils.newInboundOrderDTO(supervisor, sectionDTO, batchesMockedDTO);

        Exception exception = assertThrows(
                BadRequestException.class,
                () -> inboundOrderService.create(inboundOrderDTOMocked)
        );

        assertThat(exception.getMessage()).isEqualTo("O supervisor n??o pertence ao armaz??m");
    }

    @Test
    @DisplayName("Retorna exce????o de n??o encontrado quando o produto passado n??o existe")
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
                .thenThrow(new NotFoundException("O produto n??o foi encontrado!"));

        SectionDTO sectionDTO = SectionUtils.newSectionDTO(section, section.getWarehouse());
        List<BatchDTO> batchesMockedDTO = BatchUtils.newBatchDTOList();
        InboundOrderDTO inboundOrderDTOMocked = InboundOrderUtils.newInboundOrderDTO(supervisor, sectionDTO, batchesMockedDTO);

        Exception exception = assertThrows(
                NotFoundException.class,
                () -> inboundOrderService.create(inboundOrderDTOMocked)
        );

        assertThat(exception.getMessage()).isEqualTo("O produto n??o foi encontrado!");
    }

    @Test
    @DisplayName("Retorna exce????o quando tipo de produto e tipo de setor s??o diferentes")
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
                .thenThrow(new BadRequestException("O setor e o produto n??o t??m o mesmo tipo de armazenamento!"));

        SectionDTO sectionDTO = SectionUtils.newSectionDTO(section, section.getWarehouse());
        List<BatchDTO> batchesMockedDTO = BatchUtils.newBatchDTOList();
        InboundOrderDTO inboundOrderDTOMocked = InboundOrderUtils.newInboundOrderDTO(supervisor, sectionDTO, batchesMockedDTO);

        Exception exception = assertThrows(
                BadRequestException.class,
                () -> inboundOrderService.create(inboundOrderDTOMocked)
        );

        assertThat(exception.getMessage()).isEqualTo("O setor e o produto n??o t??m o mesmo tipo de armazenamento!");
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
    @DisplayName("Retorna exce????o quando a ordem de entrada n??o existe")
    void returnNotFoundException_whenInboundOrderNotExists() {

            BDDMockito.when(inboundOrderRepository.findById(ArgumentMatchers.anyLong()))
                    .thenThrow(new NotFoundException("A Ordem de entrada n??o foi encontrado!"));

            Exception exception = assertThrows(
                    NotFoundException.class,
                    () -> inboundOrderService.findById(ArgumentMatchers.anyLong())
            );

            assertThat(exception.getMessage()).isEqualTo("A Ordem de entrada n??o foi encontrado!");
    }

    @Test
    void updateInBoundOrder_ShouldReturnListOfBatches() {
        Section section = SectionUtils.newSection();
        Supervisor supervisor = SupervisorUtils.newSupervisor();
        SectionDTO sectionDTO = SectionUtils.newSectionDTO(section, section.getWarehouse());
        List<BatchDTO> batchesList = BatchUtils.newBatchDTOList();
        Warehouse  warehouse = WarehouseUtils.newWarehouse();
        InboundOrderDTO inboundOrderDTO = InboundOrderUtils.newInboundOrderDTO(supervisor, sectionDTO, batchesList);
        Optional<InboundOrder> inboundOrderMocked = Optional.of(InboundOrderUtils.newInboundOrder(supervisor, section));

        BDDMockito.when(warehouseService.findById(Mockito.anyLong()))
                .thenReturn(warehouse);

        BDDMockito.when(sectionService.validatesSection(
                Mockito.any(InboundOrderDTO.class),
                Mockito.any(Warehouse.class))
        ).thenReturn(section);

        BDDMockito.when(supervisorService.validatesSupervisor(
                Mockito.any(InboundOrderDTO.class),
                Mockito.any(Warehouse.class))
        ).thenReturn(SupervisorUtils.newSupervisorWithWarehouse());

        BDDMockito.when(inboundOrderRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(inboundOrderMocked);

        BDDMockito.when(inboundOrderRepository.save(Mockito.any(InboundOrder.class)))
                .thenReturn(InboundOrderUtils.newInboundOrder(supervisor, section));

        BDDMockito.when(inboundOrderRepository.findByBatch(ArgumentMatchers.anyLong()))
                .thenReturn(InboundOrderUtils.newInboundOrder(supervisor, section));

        doNothing().when(batchService).updateBatches(Mockito.any(InboundOrderDTO.class), Mockito.any(Section.class), Mockito.any(InboundOrder.class));

        List<BatchDTO> batchesDTO = inboundOrderService.update(inboundOrderDTO);

        assertThat(batchesDTO).isEqualTo(batchesList);
    }

}