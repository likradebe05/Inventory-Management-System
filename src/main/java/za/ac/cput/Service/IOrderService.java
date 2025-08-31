package za.ac.cput.Service;

import za.ac.cput.Domain.Order;



public interface IOrderService extends IService<Order, String> {

    Order create(Order order);

    Order read(String orderId);

    Order update(Order order);

    boolean delete(String orderId);
}

