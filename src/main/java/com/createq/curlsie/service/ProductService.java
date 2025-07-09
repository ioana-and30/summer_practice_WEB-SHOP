package com.createq.curlsie.service;

import com.createq.curlsie.model.ProductModel;

import java.util.List;

public interface ProductService {

    public List<ProductModel> getAll();
    public List<ProductModel> getByCategoryId(Long categoryId);
}
