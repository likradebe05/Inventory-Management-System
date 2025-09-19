package za.ac.cput.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.Domain.Product;
import za.ac.cput.Repository.ProductRepository;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepo;

    @Autowired
    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Product create(Product obj) {
        return productRepo.save(obj);
    }

    @Override
    public Product read(String productId) {
        return productRepo.findById(productId).orElse(null);
    }

    // Overload using Long-based primary key lookup
    public Product findByProductId(Long productId) {
        if (productId == null) return null;
        return productRepo.findByProductId(productId);
    }

    @Override
    public Product update(Product obj) {
        return productRepo.save(obj);
    }

    @Override
    public boolean delete(String productId) {
        if (productRepo.existsById(productId)) {
            productRepo.deleteById(productId);
            return true;
        }
        return false;
    }

    @Override
    public List<Product> getAll() {
        return productRepo.findAll();
    }

    public List<Product> findByCategoryId(String categoryId) {
        return productRepo.findByCategoryId(categoryId);
    }

    public List<Product> findBySupplierId(String supplierId) {
        return productRepo.findBySupplierId(supplierId);
    }

    public List<Product> findByPriceGreaterThan(double price) {
        return productRepo.findByPriceGreaterThan(price);
    }
}

