package com.createq.curlsie.repository;

import com.createq.curlsie.model.ProductModel;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    List<ProductModel> findByCategory_Id(Long categoryId, Sort sort);
    Optional<ProductModel> findById(Long productId);
}
