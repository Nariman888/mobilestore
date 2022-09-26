package com.mobileonlinestore.mobilestore.services;

import com.mobileonlinestore.mobilestore.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();
    Category getCategory(Long id);
    Category addCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(Long id);
}
