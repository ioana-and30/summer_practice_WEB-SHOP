package com.createq.curlsie.service.impl;

import com.createq.curlsie.model.ProductModel;
import com.createq.curlsie.repository.ProductRepository;
import com.createq.curlsie.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;


    public DefaultProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    public ProductRepository getProductRepository(){
        return productRepository;
    }

    public List<ProductModel> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductModel> getByCategoryId(Long categoryId) {

        return productRepository.findByCategory_Id(categoryId);

    }
}
