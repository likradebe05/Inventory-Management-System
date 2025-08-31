package za.ac.cput.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.Domain.Order;
import za.ac.cput.Service.OrderService;

import java.util.List;



@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService service){
        this.orderService = service;
    }

    @PostMapping("/create")
    public Order create(@RequestBody Order order){
        this.orderService.create(order);
        return order;
    }

    @GetMapping("/read/{OrderId}")
    public Order read(@PathVariable String orderId){
        this.orderService.read(orderId);

        return null;
    }

    @PutMapping("/update")
    public Order update(@RequestBody Order order){
        this.orderService.update(order);
        return order;
    }

    @DeleteMapping("/delete/{OrderId}")
    public void delete(@PathVariable String orderId) {
        this.orderService.delete(orderId);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getCustomerOrders() {
        List<Order> orders = orderService.getAll();
        return ResponseEntity.ok(orders);
    }


}
