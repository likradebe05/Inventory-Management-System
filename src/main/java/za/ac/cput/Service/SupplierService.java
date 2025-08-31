package za.ac.cput.Service;

import org.springframework.stereotype.Service;
import za.ac.cput.Domain.Supplier;
import za.ac.cput.Repository.SupplierRepository;

import java.util.List;

@Service
public class SupplierService implements ISupplierService {

    private final SupplierRepository supplierRepo;

    public SupplierService() {
        this.supplierRepo = SupplierRepository.getInstance();
    }

    @Override
    public Supplier create(Supplier obj) {
        return supplierRepo.create(obj);
    }

    @Override
    public Supplier read(String supplierId) {
        return supplierRepo.read(supplierId);
    }

    @Override
    public Supplier update(Supplier obj) {
        return supplierRepo.update(obj);
    }

    @Override
    public boolean delete(String supplierId) {
        return supplierRepo.delete(supplierId);
    }

    @Override
    public List<Supplier> getAll() {
        return supplierRepo.getAll();
    }
}
