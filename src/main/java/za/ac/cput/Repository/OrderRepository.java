package za.ac.cput.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.Domain.Order;
import java.util.List;


@Repository
public interface IOrderRepository extends JpaRepository<Order, String> {
    List<Order> findByCustomer_UserId(Long userId);
}

