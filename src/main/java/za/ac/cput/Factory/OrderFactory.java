package za.ac.cput.Factory;

import za.ac.cput.Domain.Order;

import java.util.Date;

public class OrderFactory {

    public static Order createOrderFactory(Date orderDate, double amountPaid, String status,
                                           String deliveryAddress, String paymentMethod) {


        return new Order.Builder()
                .setOrderDate(orderDate)
                .setAmountPaid(amountPaid)
                .setStatus(status)
                .setDeliveryAddress(deliveryAddress)
                .setPaymentMethod(paymentMethod)
                .build();
    }
}
