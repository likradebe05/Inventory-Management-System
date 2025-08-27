package za.ac.cput.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.Domain.Customer;
import za.ac.cput.Repository.CustomerRepository;
import za.ac.cput.Util.Helper;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepo;

    @Autowired
    public CustomerService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public Customer create(Customer obj) {
        // Hash the password before saving
        String hashedPassword = Helper.hashPassword(obj.getPassword());


        Customer newCustomer = new Customer.Builder()
                .setFirstName(obj.getFirstName())
                .setLastName(obj.getLastName())
                .setEmail(obj.getEmail())
                .setPassword(hashedPassword) // Set the hashed password
                .setAddress(obj.getAddress())
                .setCellphone(obj.getCellphone())
                .build();

        return customerRepo.save(newCustomer);
    }

    @Override
    public Customer read(String s) {
        return null;
    }

    @Override
    public Optional<Customer> read(Long userId) {
        // The read method should return an Optional for better error handling
        return customerRepo.findById(String.valueOf(userId));
    }

    @Override
    public Customer update(Customer obj) {
        // Find the existing customer to retain the original password hash
        Optional<Customer> existingCustomer = customerRepo.findById(String.valueOf(obj.getUserId()));
        if (existingCustomer.isPresent()) {
            String password = obj.getPassword();
            String passwordToUse = password != null ? Helper.hashPassword(password) : existingCustomer.get().getPassword();

            // Build a new customer object with updated details
            Customer updatedCustomer = new Customer.Builder()
                    .setUserId(obj.getUserId())
                    .setFirstName(obj.getFirstName())
                    .setLastName(obj.getLastName())
                    .setEmail(obj.getEmail())
                    .setPassword(passwordToUse) //  update the hashed password
                    .setAddress(obj.getAddress())
                    .setCellphone(obj.getCellphone())
                    .build();
            return customerRepo.save(updatedCustomer);
        }
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public boolean delete(Long userId) {
        if (customerRepo.existsById(String.valueOf(userId))) {
            customerRepo.deleteById(String.valueOf(userId));
            return true;
        }
        return false;
    }

    @Override
    public List<Customer> getAll() {
        return customerRepo.findAll();
    }

    public Customer findByEmail(String email) {
        return customerRepo.findByEmail(email);
    }
}