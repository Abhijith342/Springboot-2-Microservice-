package com.app.CategoryApplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.CategoryApplication.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{
    
}
