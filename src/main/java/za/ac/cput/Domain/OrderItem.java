package za.ac.cput.Domain;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    protected OrderItem() {}

    private OrderItem(Builder builder) {
        this.id = builder.id;
        this.order = builder.order;
        this.product = builder.product;
        this.quantity = builder.quantity;
    }

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public static class Builder {
        private Long id;
        private Order order;
        private Product product;
        private int quantity;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setOrder(Order order) {
            this.order = order;
            return this;
        }

        public Builder setProduct(Product product) {
            this.product = product;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder copy(OrderItem orderItem) {
            this.id = orderItem.id;
            this.order = orderItem.order;
            this.product = orderItem.product;
            this.quantity = orderItem.quantity;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
