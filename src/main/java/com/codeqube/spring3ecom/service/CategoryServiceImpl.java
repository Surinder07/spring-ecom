package com.codeqube.spring3ecom.service;

import com.codeqube.spring3ecom.exceptions.APIException;
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
        List<Category> categoryList = categoryRepository.findAll();
        if (categoryList.isEmpty()){
            throw new APIException("No Category found!");
        }
        return categoryList;
    }

    @Override
    public void createCategory(Category category) {
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if (savedCategory != null){
            throw new APIException("Category with this name " + category.getCategoryName() + "already exists!!");
        }
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
