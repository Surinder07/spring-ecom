package com.codeqube.spring3ecom.service;

import com.codeqube.spring3ecom.exceptions.APIException;
import com.codeqube.spring3ecom.exceptions.ResourceNotFoundException;
import com.codeqube.spring3ecom.model.Category;
import com.codeqube.spring3ecom.payload.CategoryDTO;
import com.codeqube.spring3ecom.payload.CategoryResponse;
import com.codeqube.spring3ecom.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories() {

        List<Category> categoryList = categoryRepository.findAll();

        if (categoryList.isEmpty()){
            throw new APIException("No Category found!");
        }

        List<CategoryDTO> categoryDTOS = categoryList.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class)).toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);

        return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        // convert dto to Category class
        Category category = modelMapper.map(categoryDTO, Category.class);

        // model category will be fetched from DB
        Category categoryFromDB = categoryRepository.findByCategoryName(category.getCategoryName());
        if (categoryFromDB != null){
            throw new APIException("Category with this name " + category.getCategoryName() + "already exists!!");
        }
        //save the category in DB
        Category savedCategory = categoryRepository.save(category);

        // convert the saved category back to DTO to be returned
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {

        Category originalCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

           categoryRepository.delete(originalCategory);
          return modelMapper.map(categoryRepository, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {
        // Convert category dto to Category
        Category category = modelMapper.map(categoryDTO, Category.class);

        Category originalCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        originalCategory.setCategoryName(category.getCategoryName());


        Category updatedCategory = categoryRepository.save(originalCategory);

        // Convert back updatedCategory to DTO using model mapper map method
        return modelMapper.map(updatedCategory, CategoryDTO.class);
    }
}
