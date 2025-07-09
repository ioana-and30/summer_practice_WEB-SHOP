package com.createq.curlsie.repository;

import com.createq.curlsie.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
}
