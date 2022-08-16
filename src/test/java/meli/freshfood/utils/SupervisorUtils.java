package meli.freshfood.utils;

import meli.freshfood.model.*;

import java.util.ArrayList;
import java.util.List;

public class SupervisorUtils {

    public static Supervisor newSupervisor(){
        return new Supervisor(1L, "Bianca", "Polegatti", "bianca.polegatti@email.com","1199999999","41677701014", "São Paulo" , "BRSP01", null, null);
    }

    public static Supervisor newSupervisorWithWarehouse(){
        Warehouse warehouse =  WarehouseUtils.newWarehouse();
        return new Supervisor(1L, "Bianca", "Polegatti", "bianca.polegatti@email.com","1199999999","41677701014", "São Paulo" , "BRSP01", warehouse, null);
    }


    public static List<Supervisor> supervisorList(){
        List<Supervisor> supervisorList = new ArrayList<>();
        supervisorList.add(new Supervisor(1L, "Weslley", "Rocha", "weslley.rocha@email.com","1199999999","41677701014", "São Paulo" , "BRSP01", null, null));
        return supervisorList;
    }
}