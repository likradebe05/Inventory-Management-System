package za.ac.cput.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;
    private String sku;
    private double price;
    private int quantity;
    private String location;
    private String categoryId;
    private String supplierId;
    
    // Image related fields
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imageData;
    
    private String imageContentType;
    private String originalImageName;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<OrderItem> orderItems;

    protected Product() {}

    private Product(Builder builder) {
        this.productId = builder.productId;
        this.name = builder.name;
        this.sku = builder.sku;
        this.price = builder.price;
        this.quantity = builder.quantity;
        this.location = builder.location;
        this.categoryId = builder.categoryId;
        this.supplierId = builder.supplierId;
        this.imageData = builder.imageData;
        this.imageContentType = builder.imageContentType;
        this.originalImageName = builder.originalImageName;
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getSku() {
        return sku;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getLocation() {
        return location;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public String getImageFileName() {
        return originalImageName;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public String getOriginalImageName() {
        return originalImageName;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", location='" + location + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", imageContentType='" + imageContentType + '\'' +
                ", originalImageName='" + originalImageName + '\'' +
                ", hasImageData=" + (imageData != null && imageData.length > 0) +
                '}';
    }

    public static class Builder {
        private Long productId;
        private String name;
        private String sku;
        private double price;
        private int quantity;
        private String location;
        private String categoryId;
        private String supplierId;
        private byte[] imageData;
        private String imageContentType;
        private String originalImageName;

        public Builder setProductId(Long productId) {
            this.productId = productId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSku(String sku) {
            this.sku = sku;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder setCategoryId(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder setSupplierId(String supplierId) {
            this.supplierId = supplierId;
            return this;
        }

        public Builder setImageFileName(String imageFileName) {
            this.originalImageName = imageFileName;
            return this;
        }

        public Builder setImageData(byte[] imageData) {
            this.imageData = imageData;
            return this;
        }

        public Builder setImageContentType(String imageContentType) {
            this.imageContentType = imageContentType;
            return this;
        }

        public Builder setOriginalImageName(String originalImageName) {
            this.originalImageName = originalImageName;
            return this;
        }

        public Builder copy(Product product) {
            this.productId = product.productId;
            this.name = product.name;
            this.sku = product.sku;
            this.price = product.price;
            this.quantity = product.quantity;
            this.location = product.location;
            this.categoryId = product.categoryId;
            this.supplierId = product.supplierId;
            this.imageData = product.imageData;
            this.imageContentType = product.imageContentType;
            this.originalImageName = product.originalImageName;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}


