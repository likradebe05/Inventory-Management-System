package za.ac.cput.Service;

import org.springframework.stereotype.Service;
import za.ac.cput.Domain.Supplier;

import java.util.List;

@Service
public interface ISupplierService extends IService<Supplier, String> {
    List<Supplier> getAll();
}
