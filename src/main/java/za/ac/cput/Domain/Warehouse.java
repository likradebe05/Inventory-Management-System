package za.ac.cput.Domain;

import jakarta.persistence.*;

@Entity
@Table(name = "warehouses")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseId;

    private String name;
    private String location;

    protected Warehouse() {}

    private Warehouse(Builder builder) {
        this.warehouseId = builder.warehouseId;
        this.name = builder.name;
        this.location = builder.location;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "warehouseId='" + warehouseId + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public static class Builder {
        private Long warehouseId;
        private String name;
        private String location;

        public Builder setWarehouseId(Long warehouseId) {
            this.warehouseId = warehouseId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder copy(Warehouse warehouse) {
            this.warehouseId = warehouse.warehouseId;
            this.name = warehouse.name;
            this.location = warehouse.location;
            return this;
        }

        public Warehouse build() {
            return new Warehouse(this);
        }
    }
}
