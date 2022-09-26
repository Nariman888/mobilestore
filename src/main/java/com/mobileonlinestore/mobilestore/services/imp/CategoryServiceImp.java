package com.mobileonlinestore.mobilestore.services.imp;

import com.mobileonlinestore.mobilestore.entities.Category;
import com.mobileonlinestore.mobilestore.repositories.CategoryRepository;
import com.mobileonlinestore.mobilestore.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        Category newCategory = getCategory(category.getId());
        newCategory.setName(category.getName());
        return categoryRepository.save(newCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
