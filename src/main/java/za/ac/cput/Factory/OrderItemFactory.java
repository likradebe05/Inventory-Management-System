package za.ac.cput.Factory;

import za.ac.cput.Domain.OrderItem;

public class OrderItemFactory {


    public static OrderItem createOrderItem (int quantity){



        return new OrderItem.Builder()
                .setQuantity(quantity)
                .build();

    }


}
