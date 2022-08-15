package meli.freshfood.utils;


import meli.freshfood.model.Supervisor;
import meli.freshfood.model.Warehouse;

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
}
