package za.ac.cput.Factory;

import za.ac.cput.Domain.Customer;

public class CustomerFactory {
    public static Customer createCustomer(String firstName, String lastName, String email, String password,
                                          String address, String cellphone) {
        if (firstName == null || firstName.isEmpty()
                || lastName == null || lastName.isEmpty()
                || email == null || email.isEmpty()
                || password == null || password.isEmpty()
                || address == null || address.isEmpty()
                || cellphone == null || cellphone.isEmpty()) {
            return null;
        }

        return new Customer.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setAddress(address)
                .setCellphone(cellphone)
                .build();
    }
}
