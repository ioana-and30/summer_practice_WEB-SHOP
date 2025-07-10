package com.createq.curlsie.service.impl;

import com.createq.curlsie.model.ProductModel;
import com.createq.curlsie.repository.CategoryRepository;
import com.createq.curlsie.repository.ProductRepository;
import com.createq.curlsie.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    public DefaultProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;

        this.categoryRepository = categoryRepository;
    }

    public ProductRepository getProductRepository(){
        return productRepository;
    }

    @Override
    public List<ProductModel> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductModel> getByCategoryId(Long categoryId) {

        Long idToUse = categoryId;

        if (categoryId == null || !categoryRepository.existsById(categoryId)) {
            idToUse = 1L;
        }

        return productRepository.findByCategory_Id(idToUse);
    }
}
