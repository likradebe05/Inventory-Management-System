package za.ac.cput.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.Domain.Warehouse;
import za.ac.cput.Service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping("/create")
    public Warehouse create(@RequestBody Warehouse warehouse) {
        return warehouseService.create(warehouse);
    }

    @GetMapping("/read/{warehouseId}")
    public Warehouse read(@PathVariable String warehouseId) {
        return warehouseService.read(warehouseId);
    }

    @PostMapping("/update")
    public Warehouse update(@RequestBody Warehouse warehouse) {
        return warehouseService.update(warehouse);
    }

    @DeleteMapping("/delete/{warehouseId}")
    public void delete(@PathVariable String warehouseId) {
        warehouseService.delete(warehouseId);
    }

    @GetMapping("/getAll")
    public List<Warehouse> getAll() {
        return warehouseService.getAll();
    }
}
