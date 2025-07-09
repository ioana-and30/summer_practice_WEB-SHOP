package com.createq.curlsie.repository;

import com.createq.curlsie.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    List<ProductModel> findByCategory_Id(Long categoryId);

}
