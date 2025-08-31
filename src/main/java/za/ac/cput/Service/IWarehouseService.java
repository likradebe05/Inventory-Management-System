package za.ac.cput.Service;

import za.ac.cput.Domain.Warehouse;

import java.util.List;

public interface IWarehouseService extends IService<Warehouse, String> {
    List<Warehouse> getAll();
}
