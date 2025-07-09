package com.createq.curlsie.converter;

import com.createq.curlsie.dto.CategoryDTO;
import com.createq.curlsie.model.CategoryModel;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryConverter {

    @Autowired
    private final ProductConverter productConverter;

    public CategoryConverter(ProductConverter productConverter) {
        this.productConverter = productConverter;
    }

    public CategoryDTO convert(CategoryModel categoryModel) {

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(categoryModel.getId());
        categoryDTO.setName(categoryModel.getName());
        categoryDTO.setProducts(productConverter.convertAll(categoryModel.getProducts()));

        return categoryDTO;
    }

    public List<CategoryDTO> convertAll(List<CategoryModel> categoryModels) {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (CategoryModel categoryModel : categoryModels) {
            categoryDTOS.add(convert(categoryModel));
        }

        return categoryDTOS;
    }
}
