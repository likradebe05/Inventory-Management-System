package za.ac.cput.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.Domain.Warehouse;
import za.ac.cput.Repository.WarehouseRepository;

import java.util.List;

@Service
public class WarehouseService implements IWarehouseService {

    private final WarehouseRepository warehouseRepo;

    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepo) {
        this.warehouseRepo = warehouseRepo;
    }

    @Override
    public Warehouse create(Warehouse obj) {
        return warehouseRepo.save(obj);
    }

    @Override
    public Warehouse read(String warehouseId) {
        return warehouseRepo.findById(warehouseId).orElse(null);
    }

    @Override
    public Warehouse update(Warehouse obj) {
        return warehouseRepo.save(obj);
    }

    @Override
    public boolean delete(String warehouseId) {
        if (warehouseRepo.existsById(warehouseId)) {
            warehouseRepo.deleteById(warehouseId);
            return true;
        }
        return false;
    }

    @Override
    public List<Warehouse> getAll() {
        return warehouseRepo.findAll();
    }
}