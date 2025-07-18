package com.createq.curlsie.facades;

import com.createq.curlsie.dto.ProductDTO;
import com.createq.curlsie.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ProductFacade {

    public List<ProductDTO> getAll() ;

    List<ProductDTO> getByCategoryId(Long categoryId) ;
}
