package za.ac.cput.Domain;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String description;

    protected Category() {}

    private Category(Builder builder) {
        this.categoryId = builder.categoryId;
        this.description = builder.description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId='" + categoryId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public static class Builder {
        private Long categoryId;
        private String description;

        public Builder setCategoryId(Long categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder copy(Category category) {
            this.categoryId = category.categoryId;
            this.description = category.description;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }
}