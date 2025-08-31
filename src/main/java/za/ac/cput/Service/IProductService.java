
package za.ac.cput.Service;

import za.ac.cput.Domain.Product;

import java.util.List;

public interface IProductService extends IService<Product, String> {
    List<Product> getAll();
}

