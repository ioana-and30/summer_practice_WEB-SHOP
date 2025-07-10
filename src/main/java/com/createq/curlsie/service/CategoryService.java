package com.createq.curlsie.service;

import com.createq.curlsie.model.CategoryModel;

import java.util.List;

public interface CategoryService {

    public List<CategoryModel> getAll();
    public CategoryModel getByCategoryID(Long id);
}
