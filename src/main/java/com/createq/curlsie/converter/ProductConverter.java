package com.createq.curlsie.converter;

import com.createq.curlsie.dto.ProductDTO;
import com.createq.curlsie.model.ProductModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConverter {

    public ProductDTO convert(ProductModel productModel) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productModel.getId());
        productDTO.setName(productModel.getName());
        productDTO.setPrice(productModel.getPrice());
        productDTO.setQuantity(productModel.getQuantity());
        productDTO.setShortDescription(productModel.getShortDescription());
        productDTO.setLongDescription(productModel.getLongDescription());
        productDTO.setImage(productModel.getImage());

        return productDTO;
    }

    public List<ProductDTO> convertAll(List<ProductModel> productModel) {

        List<ProductDTO> productDTO = new ArrayList<>();

        for (ProductModel product : productModel) {
            productDTO.add(convert(product));
        }

        return productDTO;
    }
}
