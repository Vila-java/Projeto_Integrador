package meli.freshfood.utils;
import meli.freshfood.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class BatchUtils {

    public static Batch newBatch(){
        Seller seller = SellerUtils.newSellerWithoutProducts();
        Product product = ProductUtils.newProduct();
        Section section = SectionUtils.newSection();
        Supervisor supervisor = SupervisorUtils.newSupervisor();
        InboundOrder inboundOrder = InboundOrderUtils.newInboundOrder(supervisor, section);

        return new Batch(1L, 30F, 22F,
                5, 10, LocalDate.of(2022, 01, 1),
                LocalDateTime.of(2022, Month.FEBRUARY, 1, 1, 1),
                LocalDate.of(2022, 01, 1),
                product, section, inboundOrder);
    }
}
