package com.createq.curlsie.service;

import com.createq.curlsie.model.ProductModel;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductService {

   List<ProductModel> getAll() ;
   List<ProductModel> getByCategoryId(Long categoryId, Sort sort) ;
   ProductModel getByProductId(Long productId) ;
   List<ProductModel> findAllByIds(List<Long> ids);

}
