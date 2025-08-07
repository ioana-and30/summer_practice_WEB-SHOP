package com.createq.curlsie.converter;

import com.createq.curlsie.dto.CategoryDTO;
import com.createq.curlsie.model.CategoryModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryConverter {

    private final ProductConverter productConverter;

    public CategoryConverter(ProductConverter productConverter) {
        this.productConverter = productConverter;
    }

    public CategoryDTO convertToDTO(CategoryModel categoryModel) {
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(categoryModel.getId());
        categoryDTO.setName(categoryModel.getName());
        categoryDTO.setProducts(productConverter.convertAllToDTO(categoryModel.getProducts()));

        return categoryDTO;
    }

    public CategoryDTO convertToDTOShallow(CategoryModel categoryModel) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(categoryModel.getId());
        categoryDTO.setName(categoryModel.getName());

        return categoryDTO;
    }

    public List<CategoryDTO> convertAllToDTO(List<CategoryModel> categoryModels) {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (CategoryModel categoryModel : categoryModels) {
            categoryDTOS.add(convertToDTO(categoryModel));
        }

        return categoryDTOS;
    }

    public List<CategoryModel> convertAllToModel(List<CategoryDTO> categoryDTOS){
        List<CategoryModel> categoryModels=new ArrayList<>();
        for(CategoryDTO categoryDTO: categoryDTOS){
            categoryModels.add(convertToModel(categoryDTO));
        }

        return categoryModels;
    }

    public CategoryModel convertToModel(CategoryDTO categoryDTO) {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(categoryDTO.getId());
        categoryModel.setName(categoryDTO.getName());
        categoryModel.setProducts(productConverter.convertAllToModel(categoryDTO.getProducts()));

        return categoryModel;
    }
}
