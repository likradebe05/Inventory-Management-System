package za.ac.cput.Factory;

import za.ac.cput.Util.Helper;

import za.ac.cput.Domain.Category;

public class CategoryFactory {
    public static Category createCategory(String description) {
        if (Helper.isNullOrEmpty(description)) {
            return null;
        }

        return new Category.Builder()
                .setDescription(description)
                .build();
    }
}