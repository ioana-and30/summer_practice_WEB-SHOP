package com.createq.curlsie.service.impl;

import com.createq.curlsie.exceptions.ResourceNotFoundException;
import com.createq.curlsie.model.ProductModel;
import com.createq.curlsie.repository.CategoryRepository;
import com.createq.curlsie.repository.ProductRepository;
import com.createq.curlsie.service.ProductService;
import org.springframework.data.domain.Sort;
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

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    @Override
    public List<ProductModel> getAll() throws ResourceNotFoundException {
        return productRepository.findAll();
    }

    @Override
    public List<ProductModel> getByCategoryId(Long categoryId, Sort sort) throws ResourceNotFoundException {

        Long idToUse = categoryId;

        if (categoryId == null || !categoryRepository.existsById(categoryId)) {
            idToUse = 1L;
        }

        List<ProductModel> products = productRepository.findByCategory_Id(idToUse,sort);
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("No products found");
        }

        return products;
    }

    @Override
    public ProductModel getByProductId(Long productId) throws ResourceNotFoundException {

        if (productId == null || !productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("No product found");
        }

        return productRepository.findById(productId).get();
    }
}
