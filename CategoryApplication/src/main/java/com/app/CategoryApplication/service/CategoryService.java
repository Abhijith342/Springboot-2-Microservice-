package com.app.CategoryApplication.service;

import java.util.List;
import java.util.Optional;
import com.app.CategoryApplication.entity.Category;

public interface CategoryService {
    Category createCategory(Category category);

    List<Category> getAllCategories();

    Optional<Category> getCategoryById(Long id);

    Category updateCategory(Long id, Category category);

    boolean deleteCategory(Long id);

}
