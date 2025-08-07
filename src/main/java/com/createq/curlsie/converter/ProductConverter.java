package com.createq.curlsie.converter;

import com.createq.curlsie.dto.ProductDTO;
import com.createq.curlsie.model.ProductModel;
import jdk.jfr.Label;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConverter {

    private final CategoryConverter categoryConverter;

    public ProductConverter( @Lazy CategoryConverter categoryConverter) {
        this.categoryConverter = categoryConverter;
    }

    public ProductDTO convertToDTO(ProductModel productModel) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productModel.getId());
        productDTO.setName(productModel.getName());
        productDTO.setPrice(productModel.getPrice());
        productDTO.setQuantity(productModel.getQuantity());
        productDTO.setShortDescription(productModel.getShortDescription());
        productDTO.setLongDescription(productModel.getLongDescription());
        productDTO.setImage(productModel.getImage());

        productDTO.setCategory(categoryConverter.convertToDTOShallow(productModel.getCategory()));


        return productDTO;
    }

    public List<ProductDTO> convertAllToDTO(List<ProductModel> productModel) {
        List<ProductDTO> productDTO = new ArrayList<>();
        for (ProductModel product : productModel) {
            productDTO.add(convertToDTO(product));
        }

        return productDTO;
    }

    public List<ProductModel> convertAllToModel(List<ProductDTO> productDTO) {
        List<ProductModel> productModel = new ArrayList<>();
        for (ProductDTO product : productDTO) {
            productModel.add(convertToModel(product));
        }

        return productModel;
    }

    public ProductModel convertToModel(ProductDTO productDTO) {
        ProductModel productModel = new ProductModel();
        productModel.setId(productDTO.getId());
        productModel.setName(productDTO.getName());
        productModel.setPrice(productDTO.getPrice());
        productModel.setQuantity(productDTO.getQuantity());
        productModel.setShortDescription(productDTO.getShortDescription());
        productModel.setLongDescription(productDTO.getLongDescription());
        productModel.setImage(productDTO.getImage());

        productModel.setCategory(categoryConverter.convertToModel(productDTO.getCategory()));

        return productModel;
    }
}
