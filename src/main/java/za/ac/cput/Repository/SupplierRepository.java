package za.ac.cput.Repository;


import za.ac.cput.Domain.Supplier;
import za.ac.cput.Repository.ISupplierRepository;

import java.util.ArrayList;
import java.util.List;

public class SupplierRepository implements ISupplierRepository {

    private static SupplierRepository repository = null;
    private List<Supplier> supplierList;

    private SupplierRepository() {
        supplierList = new ArrayList<>();
    }

    public static SupplierRepository getInstance() {
        if (repository == null) {
            repository = new SupplierRepository();
        }
        return repository;
    }

    @Override
    public Supplier create(Supplier supplier) {
        if (supplier == null || supplier.getSupplierId() == null) {
            return null;
        }

        if (exists(String.valueOf(supplier.getSupplierId()))) {
            return null;
        }

        supplierList.add(supplier);
        return supplier;
    }

    @Override
    public Supplier read(String supplierId) {
        for (Supplier supplier : supplierList) {
            if (supplier.getSupplierId().equals(supplierId)) {
                return supplier;
            }
        }
        return null;
    }

    @Override
    public Supplier update(Supplier supplier) {
        if (supplier == null) {
            return null;
        }

        int index = findIndexBySupplierId(String.valueOf(supplier.getSupplierId()));

        if (index == -1) {
            return null;
        }

        supplierList.set(index, supplier);
        return supplier;
    }

    @Override
    public boolean delete(String supplierId) {
        int index = findIndexBySupplierId(supplierId);

        if (index == -1) {
            return false;
        }

        supplierList.remove(index);
        return true;
    }

    @Override
    public List<Supplier> getAll() {
        return new ArrayList<>(supplierList);
    }

    private int findIndexBySupplierId(String supplierId) {
        for (int i = 0; i < supplierList.size(); i++) {
            if (supplierList.get(i).getSupplierId().equals(supplierId)) {
                return i;
            }
        }
        return -1;
    }

    private boolean exists(String supplierId) {
        return findIndexBySupplierId(supplierId) != -1;
    }
}
