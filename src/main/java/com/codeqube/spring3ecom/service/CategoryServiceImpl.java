package com.codeqube.spring3ecom.service;

import com.codeqube.spring3ecom.exceptions.ResourceNotFoundException;
import com.codeqube.spring3ecom.model.Category;
import com.codeqube.spring3ecom.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {

        Category originalCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

           categoryRepository.delete(originalCategory);
          return "Category with Id " + categoryId + " deleted";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {

        Category originalCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        originalCategory.setCategoryName(category.getCategoryName());
        return categoryRepository.save(originalCategory);
    }
}
