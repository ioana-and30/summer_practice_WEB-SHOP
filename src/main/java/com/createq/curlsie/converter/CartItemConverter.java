package com.createq.curlsie.converter;

import com.createq.curlsie.dto.CartItemDTO;
import com.createq.curlsie.model.CartItemModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartItemConverter {

    private final ProductConverter productConverter;

    public CartItemConverter(ProductConverter productConverter) {
        this.productConverter = productConverter;
    }

    public CartItemDTO convertToDTO(CartItemModel cartItemModel) {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(cartItemModel.getId());
        cartItemDTO.setProduct(productConverter.convertToDTO(cartItemModel.getProduct()));
        cartItemDTO.setQuantity(cartItemModel.getQuantity());
        cartItemDTO.setPrice(cartItemModel.getPrice());

        return cartItemDTO;
    }

    public CartItemModel convertToModel(CartItemDTO cartItemDTO) {
        CartItemModel cartItemModel = new CartItemModel();
        cartItemModel.setId(cartItemDTO.getId());
        cartItemModel.setProduct(productConverter.convertToModel(cartItemDTO.getProduct()));
        cartItemModel.setQuantity(cartItemDTO.getQuantity());
        cartItemModel.setPrice(cartItemDTO.getPrice());

        return cartItemModel;
    }

    public List<CartItemDTO> convertAllToDTO(List<CartItemModel> cartItemModels) {
        List<CartItemDTO> cartItemDTOS = new ArrayList<>();
        for (CartItemModel model : cartItemModels) {
            cartItemDTOS.add(convertToDTO(model));
        }
        return cartItemDTOS;
    }

    public List<CartItemModel> convertAllToModel(List<CartItemDTO> cartItemDTOS) {
        List<CartItemModel> cartItemModels = new ArrayList<>();
        for (CartItemDTO dto : cartItemDTOS) {
            cartItemModels.add(convertToModel(dto));
        }
        return cartItemModels;
    }
}
