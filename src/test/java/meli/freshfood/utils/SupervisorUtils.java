package meli.freshfood.utils;

import meli.freshfood.model.*;

import java.time.LocalDate;
import java.util.List;

public class SupervisorUtils {

    public static Supervisor newSupervisor(){
        return new Supervisor(1L, "Bianca", "Polegatti", null, null);
    }

    public static Supervisor newSupervisorWithWarehouse(){
       Warehouse warehouse =  WarehouseUtils.newWarehouse();
        return new Supervisor(1L, "Bianca", "Polegatti", warehouse, null);
    }
}
