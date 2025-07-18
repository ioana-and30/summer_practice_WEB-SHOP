package com.createq.curlsie.dto;

import com.createq.curlsie.model.ProductModel;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import java.util.List;

public class CategoryDTO {

    private Long id;
    private String name;
    private List<ProductDTO> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

}
