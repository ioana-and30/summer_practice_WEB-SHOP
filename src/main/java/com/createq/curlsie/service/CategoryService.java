package com.createq.curlsie.service;

import com.createq.curlsie.exceptions.ResourceNotFoundException;
import com.createq.curlsie.model.CategoryModel;

import java.util.List;

public interface CategoryService {

    public List<CategoryModel> getAll() throws ResourceNotFoundException;
    public CategoryModel getByCategoryID(Long id);
}
