package za.ac.cput.Domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;




@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    private Date orderDate;
    private double amountPaid;
    private String status;
    private String deliveryAddress;
    private String paymentMethod;

    protected Order() {
    }

    private Order(Builder builder) {
        this.orderId = builder.orderId;
        this.customer = builder.customer;
        this.orderDate = builder.orderDate;
        this.orderItems = builder.orderItems;
        this.amountPaid = builder.amountPaid;
        this.status = builder.status;
        this.deliveryAddress = builder.deliveryAddress;
        this.paymentMethod = builder.paymentMethod;
    }

    // Getters and Setters
    public Long getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public Date getOrderDate() { return orderDate; }
    public List<OrderItem> getOrderItems() { return orderItems; }
    public double getAmountPaid() { return amountPaid; }
    public String getStatus() { return status; }
    public String getDeliveryAddress() { return deliveryAddress; }
    public String getPaymentMethod() { return paymentMethod; }

    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }
    // ... add setters for other fields if needed

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerId=" + (customer != null ? customer.getUserId() : "null") +
                ", orderDate=" + orderDate +
                ", orderItemCount=" + orderItems.size() +
                ", amountPaid=" + amountPaid +
                ", status='" + status + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }

    // Builder class
    public static class Builder {
        private Long orderId;
        private Customer customer;
        private Date orderDate = new Date();
        private List<OrderItem> orderItems = new ArrayList<>();
        private double amountPaid;
        private String status = "NEW";
        private String deliveryAddress;
        private String paymentMethod;

        public Builder setOrderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }


        public Builder setOrderDate(Date orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public Builder setOrderItems(List<OrderItem> orderItems) {
            this.orderItems = orderItems;
            return this;
        }

        public Builder setAmountPaid(double amountPaid) {
            this.amountPaid = amountPaid;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setDeliveryAddress(String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
            return this;
        }

        public Builder setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Order build() {
            return new Order(this);
        }

        public Order copy(Order order) {
            this.orderId = order.orderId;
            this.customer = order.customer;
            this.orderDate = order.orderDate;
            this.orderItems = order.orderItems;
            this.amountPaid = order.amountPaid;
            this.status = order.status;
            this.deliveryAddress = order.deliveryAddress;
            this.paymentMethod = order.paymentMethod;
            return this.build();
        }
    }
}
