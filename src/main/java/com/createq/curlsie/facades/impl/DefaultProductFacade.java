package com.createq.curlsie.facades.impl;

import com.createq.curlsie.converter.ProductConverter;
import com.createq.curlsie.dto.ProductDTO;
import com.createq.curlsie.exceptions.ResourceNotFoundException;
import com.createq.curlsie.facades.ProductFacade;
import com.createq.curlsie.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultProductFacade implements ProductFacade {

    private final ProductService productService;
    private final ProductConverter productConverter;

    @Autowired
    public DefaultProductFacade(ProductService productService, ProductConverter productConverter) {
        this.productService = productService;
        this.productConverter = productConverter;
    }

    @Override
    public List<ProductDTO> getAll() throws ResourceNotFoundException {
        return productConverter.convertAll(productService.getAll());
    }

    @Override
    public List<ProductDTO> getByCategoryId(Long categoryId, Sort sorting) throws ResourceNotFoundException{
        return productConverter.convertAll(productService.getByCategoryId(categoryId,sorting));
    }

    @Override
    public ProductDTO getByProductId(Long productId) throws ResourceNotFoundException{
        return productConverter.convert(productService.getByProductId(productId));
    }
}
