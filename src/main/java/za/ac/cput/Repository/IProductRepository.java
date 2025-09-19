package za.ac.cput.Repository;

import za.ac.cput.Domain.Product;

import java.util.List;

public interface IProductRepository {
    Product create(Product product);
    Product read(String productId);
    Product update(Product product);
    boolean delete(String productId);
    List<Product> getAll();
}

