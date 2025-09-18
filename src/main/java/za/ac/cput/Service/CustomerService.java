package za.ac.cput.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.Domain.Customer;
import za.ac.cput.Domain.Role;
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
                .setPassword(hashedPassword) // store hashed password
                .setAddress(obj.getAddress())
                .setCellphone(obj.getCellphone())
                .setRole(Role.CUSTOMER) // Automatically assign CUSTOMER role
                .setEnabled(true) // Account is enabled by default
                .setAccountNonExpired(true)
                .setAccountNonLocked(true)
                .setCredentialsNonExpired(true)
                .build();

        return customerRepo.save(newCustomer);
    }

    @Override
    public Customer read(String s) {
        // unused, can be removed or implemented if you want string-based reads
        return null;
    }

    @Override
    public Optional<Customer> read(Long userId) {
        return customerRepo.findById(userId);
    }

    @Override
    public Customer update(Customer obj) {
        Optional<Customer> existingCustomer = customerRepo.findById(obj.getUserId());
        if (existingCustomer.isPresent()) {
            String password = obj.getPassword();
            String passwordToUse = (password != null && !password.isEmpty())
                    ? Helper.hashPassword(password)
                    : existingCustomer.get().getPassword();

            Customer updatedCustomer = new Customer.Builder()
                    .setUserId(obj.getUserId())
                    .setFirstName(obj.getFirstName())
                    .setLastName(obj.getLastName())
                    .setEmail(obj.getEmail())
                    .setPassword(passwordToUse) // keep or update hashed password
                    .setAddress(obj.getAddress())
                    .setCellphone(obj.getCellphone())
                    .build();

            return customerRepo.save(updatedCustomer);
        }
        return null;
    }

    @Override
    public boolean delete(String s) {
        // unused, can be removed or implemented if needed
        return false;
    }

    @Override
    public boolean delete(Long userId) {
        if (customerRepo.existsById(userId)) {
            customerRepo.deleteById(userId);
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
