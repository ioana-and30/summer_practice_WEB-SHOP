package com.createq.curlsie.service.impl;

import com.createq.curlsie.model.CategoryModel;
import com.createq.curlsie.repository.CategoryRepository;
import com.createq.curlsie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public DefaultCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryRepository getCategoryRepository(){
        return categoryRepository;
    }

    public List<CategoryModel> getAll() {

        return categoryRepository.findAll();
    }

    @Override
    public CategoryModel getByCategoryID(Long id) {
        return categoryRepository.findById(id).orElse(null);

    }
}
