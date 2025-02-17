package com.codeqube.spring3ecom.service;

import com.codeqube.spring3ecom.model.Category;
import com.codeqube.spring3ecom.payload.CategoryDTO;
import com.codeqube.spring3ecom.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse getAllCategories();
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    String deleteCategory(Long categoryId);
    Category updateCategory(Category category, Long categoryId);
}
