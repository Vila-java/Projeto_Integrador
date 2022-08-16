package meli.freshfood.utils;


import meli.freshfood.model.Section;
import meli.freshfood.model.Supervisor;
import meli.freshfood.model.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class WarehouseUtils {

    public static Warehouse newWarehouse( ) {
        Supervisor supervisor = SupervisorUtils.newSupervisor();
        return new Warehouse(1L, "SP", null, supervisor);
    }

    public static Warehouse newWarehouseWithSupervisor(Supervisor supervisor) {
        return new Warehouse(1L, "SP", null, supervisor);
    }

    public static Warehouse newWarehouseWithoutSupervisor() {
        return new Warehouse(1L, "SP", null, null);
    }

    public static Warehouse newWarehouseWithSection() {
        List<Section> sections = new ArrayList<>();
        sections.add(SectionUtils.newSection());
        return new Warehouse(2L, "SP", sections, null);
    }
}
