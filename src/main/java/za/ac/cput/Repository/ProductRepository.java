package za.ac.cput.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.Domain.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByCategoryId(String categoryId);
    List<Product> findBySupplierId(String supplierId);
    List<Product> findByPriceGreaterThan(double price);

    // Finder using the actual primary key type (Long)
    Product findByProductId(Long productId);
}

