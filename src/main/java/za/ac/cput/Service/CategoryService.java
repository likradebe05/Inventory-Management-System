package za.ac.cput.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.Domain.Category;
import za.ac.cput.Repository.ICategoryRepository;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository repository;

    @Override
    public Category create(Category category) {
        return repository.save(category);
    }

    @Override
    public Category read(String categoryId) {
        return repository.findById(categoryId).orElse(null);
    }

    @Override
    public Category update(Category category) {
        return repository.save(category);
    }

    @Override
    public boolean delete(String categoryId) {
        if (repository.existsById(categoryId)) {
            repository.deleteById(categoryId);
            return true;
        }
        return false;
    }

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    // Optional custom query usage
    public List<Category> search(String keyword) {
        return repository.findByDescriptionContainingIgnoreCase(keyword);
    }
}
