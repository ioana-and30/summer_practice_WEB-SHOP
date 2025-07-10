package com.createq.curlsie.facades;

import com.createq.curlsie.dto.CategoryDTO;

import java.util.List;

public interface CategoryFacade {

    public List<CategoryDTO> getAll();

    CategoryDTO getByCategoryId(Long categoryId);
}
