package com.createq.curlsie.service;

import com.createq.curlsie.model.ProductModel;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductService {

    public List<ProductModel> getAll() ;
    public List<ProductModel> getByCategoryId(Long categoryId, Sort sort) ;
    public ProductModel getByProductId(Long productId) ;
}
