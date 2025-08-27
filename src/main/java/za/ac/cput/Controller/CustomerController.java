package za.ac.cput.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.Domain.Customer;
import za.ac.cput.Service.CustomerService;
import za.ac.cput.Util.Helper;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, String> request) {
        String firstName = request.get("firstName");
        String lastName = request.get("lastName");
        String email = request.get("email");
        String password = request.get("password");
        String address = request.get("address");
        String cellphone = request.get("cellphone");

        // Validate the input
        if (Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName) || Helper.isNullOrEmpty(email) || Helper.isNullOrEmpty(password)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Missing required fields"));
        }


        Customer customer = new Customer.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setAddress(address)
                .setCellphone(cellphone)
                .build();

        // Call the service to create the customer
        Customer createdCustomer = customerService.create(customer);

        // Return a response
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Customer created successfully");
        response.put("customerId", createdCustomer.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        Customer customer = customerService.findByEmail(email);

        //  Helper.verifyPassword compare the raw password to the hashed one
        if (customer != null && Helper.verifyPassword(password, customer.getPassword())) {
            Map<String, Object> response = new HashMap<>();
            response.put("token", "dummy-token-" + customer.getUserId());

            Map<String, String> user = new HashMap<>();
            user.put("email", customer.getEmail());
            user.put("firstName", customer.getFirstName());
            user.put("lastName", customer.getLastName());
            response.put("user", user);

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401).body(Map.of("error", "Invalid email or password"));
    }

    @GetMapping("/read/{userId}")
    public Customer read(@PathVariable String userId) {
        return customerService.read(userId);
    }

    @PostMapping("/update")
    public Customer update(@RequestBody Customer customer) {
        return customerService.update(customer);
    }

    @DeleteMapping("/delete/{userId}")
    public boolean delete(@PathVariable String userId) {
        return customerService.delete(userId);
    }

    @GetMapping("/getAll")
    public List<Customer> getAll() {
        return customerService.getAll();
    }



}
