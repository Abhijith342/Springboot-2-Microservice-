package com.app.CategoryApplication.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.CategoryApplication.CategoryRepository;
import com.app.CategoryApplication.entity.Category;
import com.app.CategoryApplication.service.CategoryService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor

@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;


    public Category createCategory(Category category){
        return categoryRepository.save(category);

    }
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();

    }

    
    public Optional<Category> getCategoryById(Long id){
        return categoryRepository.findById(id);

    }
    public Category updateCategory(Long id,Category category){
        Category existingCategory = categoryRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Category not found"));
        existingCategory.setCategoryName(category.getCategoryName());
        return categoryRepository.save(existingCategory);
    }

    public boolean deleteCategory(Long id){
        if(categoryRepository.findById(id).isPresent()){
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
