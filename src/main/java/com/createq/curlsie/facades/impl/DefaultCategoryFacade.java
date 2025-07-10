package com.createq.curlsie.facades.impl;

import com.createq.curlsie.converter.CategoryConverter;
import com.createq.curlsie.converter.ProductConverter;
import com.createq.curlsie.dto.CategoryDTO;
import com.createq.curlsie.exceptions.ResourceNotFoundException;
import com.createq.curlsie.facades.CategoryFacade;
import com.createq.curlsie.model.CategoryModel;
import com.createq.curlsie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultCategoryFacade implements CategoryFacade {

    private final CategoryService categoryService;
    private final CategoryConverter categoryConverter;

    public DefaultCategoryFacade(CategoryService categoryService, CategoryConverter categoryConverter) {
        this.categoryService = categoryService;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public List<CategoryDTO> getAll() throws ResourceNotFoundException {
        return categoryConverter.convertAll(categoryService.getAll());
    }

    @Override
    public CategoryDTO getByCategoryId(Long categoryId) throws ResourceNotFoundException {
        CategoryModel category;

        if (categoryId == null) {
            category = categoryService.getByCategoryID(1L);
            if (category == null) {
                throw new ResourceNotFoundException("Default category not found.");
            }
        } else {
            category = categoryService.getByCategoryID(categoryId);
            if (category == null) {
                category = categoryService.getByCategoryID(1L);
                if (category == null) {
                    throw new ResourceNotFoundException("Neither requested nor default category found.");
                }
            }
        }

        return categoryConverter.convert(category);
    }

}
