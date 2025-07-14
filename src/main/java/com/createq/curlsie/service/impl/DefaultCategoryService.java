package com.createq.curlsie.service.impl;

import com.createq.curlsie.exceptions.ResourceNotFoundException;
import com.createq.curlsie.model.CategoryModel;
import com.createq.curlsie.repository.CategoryRepository;
import com.createq.curlsie.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;

    public DefaultCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryRepository getCategoryRepository(){
        return categoryRepository;
    }

    @Override
    public List<CategoryModel> getAll() throws ResourceNotFoundException{

         return categoryRepository.findAll();
    }

    @Override
    public CategoryModel getByCategoryID(Long id) throws ResourceNotFoundException {

        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with id " + id + " not found."));
    }
}
