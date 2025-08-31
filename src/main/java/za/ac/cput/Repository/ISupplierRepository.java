package za.ac.cput.Repository;


import za.ac.cput.Domain.Supplier;
import java.util.List;

public interface ISupplierRepository {
    Supplier create(Supplier supplier);
    Supplier read(String supplierId);
    Supplier update(Supplier supplier);
    boolean delete(String supplierId);
    List<Supplier> getAll();
}
