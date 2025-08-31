package za.ac.cput.Repository;

import za.ac.cput.Domain.Warehouse;

import java.util.List;

public interface IWarehouseRepository {
    Warehouse create(Warehouse warehouse);
    Warehouse read(String warehouseId);
    Warehouse update(Warehouse warehouse);
    boolean delete(String warehouseId);
    List<Warehouse> getAll();
}
