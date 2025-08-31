package za.ac.cput.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.Domain.Category;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, String> {
    List<Category> findByDescriptionContainingIgnoreCase(String keyword);

}
