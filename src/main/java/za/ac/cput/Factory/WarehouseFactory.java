package za.ac.cput.Factory;
import za.ac.cput.Domain.Warehouse;
import za.ac.cput.Util.Helper;

public class WarehouseFactory {

    public static Warehouse  createWarehouse(String name, String location) {
        if (Helper.isNullOrEmpty(name) || Helper.isNullOrEmpty(location)) {
            return null;
        }

        return new Warehouse.Builder()
                .setName(name)
                .setLocation(location)
                .build();
    }
}

