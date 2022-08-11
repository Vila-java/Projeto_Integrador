package meli.freshfood.utils;

import meli.freshfood.model.*;

import javax.persistence.*;
import java.util.List;

public class SectionUtils {

    public static Section newSection(){
        Warehouse warehouse = WarehouseUtils.newWarehouseWithoutSupervisor();
        return new Section(1L, StorageType.FRESH, 22L, null, warehouse, null);
    }

}
