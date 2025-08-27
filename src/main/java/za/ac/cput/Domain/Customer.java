package za.ac.cput.Domain;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;
    private String password;

    private String address;
    private String cellphone;

    private Customer(Builder builder) {
        this.userId = builder.userId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.password = builder.password;
        this.address = builder.address;
        this.cellphone = builder.cellphone;

    }

    public Customer() {

    }


    public Long getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() { return password; }

    public String getAddress() {
        return address;
    }

    public String getCellphone() {
        return cellphone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password:'" + password + '\'' +
                ", address='" + address + '\'' +
                ", cellphone='" + cellphone + '\'' +
                '}';
    }

    public static class Builder {
        private Long userId;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String address;
        private String cellphone;

        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setCellphone(String cellphone) {
            this.cellphone = cellphone;
            return this;
        }

        public Customer.Builder copy(Customer customer) {
            this.userId = customer.userId;
            this.firstName = customer.firstName;
            this.lastName = customer.lastName;
            this.email = customer.email;
            this.password = customer.password;
            this.address = customer.address;
            this.cellphone = customer.cellphone;
            return this;
        }

        public Customer build() {return new Customer(this);}

    }





}
