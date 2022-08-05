package meli.freshfood.service;

import meli.freshfood.model.Warehouse;
import org.springframework.stereotype.Service;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    Warehouse warehouse = new Warehouse();

    public boolean isValid(Long warehouseId)
    {
        boolean valid = false;

        if(warehouse.getWarehouseId().equals(warehouseId))
        {
            valid = true;
        }

        return valid;
    }

    public boolean supervisorBelongsToWarehouse (Long supervisorId)
    {
        boolean belongs = false;

        if(warehouse.getSupervisor().getSupersivorId().equals(supervisorId))
        {
            belongs = true;
        }

        return belongs;
    }
}
