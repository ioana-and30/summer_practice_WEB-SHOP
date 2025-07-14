package com.createq.curlsie.facades;

import com.createq.curlsie.dto.CategoryDTO;
import com.createq.curlsie.exceptions.ResourceNotFoundException;

import java.util.List;

public interface CategoryFacade {

    public List<CategoryDTO> getAll() ;

    CategoryDTO getByCategoryId(Long categoryId) ;
}
