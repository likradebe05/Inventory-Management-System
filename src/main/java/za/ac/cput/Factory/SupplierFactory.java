package za.ac.cput.Factory;

import za.ac.cput.Domain.Supplier;
import za.ac.cput.Util.Helper;

public class SupplierFactory {
    public static Supplier createSupplier(String supplierName, String contactDetails, String address) {
        if (Helper.isNullOrEmpty(supplierName) || Helper.isNullOrEmpty(contactDetails)
                || Helper.isNullOrEmpty(address)) {
            return null;
        }

        return new Supplier.Builder()
                .setSupplierName(supplierName)
                .setContactDetails(contactDetails)
                .setAddress(address)
                .build();
    }
}