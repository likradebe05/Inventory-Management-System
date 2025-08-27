package za.ac.cput.Service;
import java.util.List;
import java.util.Optional;

import za.ac.cput.Domain.Customer;


public interface ICustomerService extends IService <Customer, String> {

    Optional<Customer> read(Long userId);

    boolean delete(Long userId);

    List<Customer> getAll();
}
