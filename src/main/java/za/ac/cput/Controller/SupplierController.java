package za.ac.cput.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.Domain.Supplier;
import za.ac.cput.Service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping("/create")
    public Supplier create(@RequestBody Supplier supplier) {
        return supplierService.create(supplier);
    }

    @GetMapping("/read/{supplierId}")
    public Supplier read(@PathVariable String supplierId) {
        return supplierService.read(supplierId);
    }

    @PostMapping("/update")
    public Supplier update(@RequestBody Supplier supplier) {
        return supplierService.update(supplier);
    }

    @DeleteMapping("/delete/{supplierId}")
    public void delete(@PathVariable String supplierId) {
        supplierService.delete(supplierId);
    }

    @GetMapping("/getAll")
    public List<Supplier> getAll() {
        return supplierService.getAll();
    }
}
