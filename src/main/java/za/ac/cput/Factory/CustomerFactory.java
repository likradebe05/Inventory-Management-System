package za.ac.cput.Factory;


import za.ac.cput.Domain.Customer;
public class CustomerFactory {
    public static Customer createCustomer(String FirstName, String LastName, String email, String password,
                                          String address, String cellphone) {
        if (FirstName == null || FirstName.isEmpty() || LastName == null || LastName.isEmpty()
                || email == null || email.isEmpty() || password == null || password.isEmpty()
                || address == null || address.isEmpty() || cellphone == null || cellphone.isEmpty()) {
            return null;
        }



        return new Customer.Builder()
                .setFirstName(FirstName)
                .setLastName(LastName)
                .setEmail(email)
                .setPassword(password)
                .setAddress(address)
                .setCellphone(address)
                .build();



    }
}
