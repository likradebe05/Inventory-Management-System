package za.ac.cput.Service;

import org.springframework.stereotype.Service;
import za.ac.cput.Domain.Category;

import java.util.List;

@Service
public interface ICategoryService extends IService<Category, String> {
    List<Category> getAll();
}
