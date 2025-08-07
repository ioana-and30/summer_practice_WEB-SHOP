package com.createq.curlsie.converter;

import com.createq.curlsie.dto.CartDTO;
import com.createq.curlsie.model.CartModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartConverter {

    private final CartItemConverter cartItemConverter;
    private final UserConverter userConverter;

    public CartConverter(CartItemConverter cartItemConverter, UserConverter userConverter) {
        this.cartItemConverter = cartItemConverter;
        this.userConverter = userConverter;
    }

    public CartDTO convertToDTO(CartModel cartModel) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cartModel.getId());

        if (cartModel.getUser() != null) {
            cartDTO.setUser(userConverter.convertToDTO(cartModel.getUser()));
        } else {
            cartDTO.setUser(null);
        }

        cartDTO.setOrderDate(cartModel.getOrderDate());
        cartDTO.setTotal(cartModel.getTotal());
        cartDTO.setItems(cartItemConverter.convertAllToDTO(cartModel.getItems()));

        return cartDTO;
    }

    public CartModel convertToModel(CartDTO cartDTO) {
        CartModel cartModel = new CartModel();

        if (cartDTO.getUser() != null) {
            cartModel.setUser(userConverter.convertToModel(cartDTO.getUser()));
        } else {
            cartModel.setUser(null);
        }

        cartModel.setId(cartDTO.getId());
        cartModel.setOrderDate(cartDTO.getOrderDate());
        cartModel.setTotal(cartDTO.getTotal());
        cartModel.setItems(cartItemConverter.convertAllToModel(cartDTO.getItems()));

        return cartModel;
    }

    public CartModel convertToModelShallow(CartDTO cartDTO) {
        CartModel cartModel = new CartModel();
        cartModel.setId(cartDTO.getId());

        return cartModel;
    }

    public List<CartDTO> convertAllToDTO(List<CartModel> cartModels) {
        List<CartDTO> cartDTOS = new ArrayList<>();
        for (CartModel model : cartModels) {
            cartDTOS.add(convertToDTO(model));
        }

        return cartDTOS;
    }

    public List<CartModel> convertAllToModel(List<CartDTO> cartDTOS) {
        List<CartModel> cartModels = new ArrayList<>();
        for (CartDTO dto : cartDTOS) {
            cartModels.add(convertToModel(dto));
        }

        return cartModels;
    }
}
