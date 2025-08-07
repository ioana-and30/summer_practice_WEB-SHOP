package com.createq.curlsie.facades.impl;

import com.createq.curlsie.dto.CartDTO;
import com.createq.curlsie.dto.CartItemDTO;
import com.createq.curlsie.facades.CartFacade;
import com.createq.curlsie.model.CartItemModel;
import com.createq.curlsie.service.CartService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultCartFacade implements CartFacade {

    private final CartService cartService;

    public DefaultCartFacade(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public CartDTO getCartByUser(Long userId) {
        return cartService.getCartByUser(userId);
    }

    @Override
    public CartDTO getCart(Long userId, String sessionId) {
        return cartService.getCart(userId, sessionId);
    }

    @Override
    public void addItemToCart(Long userId, Long productId,String sessionId, int quantity) {
        cartService.addItemToCart(userId, productId, sessionId,quantity);
    }

    @Override
    public void updateItemQuantity(Long userId, String sessionId,Long productId, int quantity) {
        cartService.updateItemQuantity(userId,sessionId, productId, quantity);
    }

    @Override
    public void removeItemFromCart(Long userId,String sessionId, Long productId) {
        cartService.removeItemFromCart(userId, sessionId,productId);
    }


    @Override
    public int getCartItemCount(Long userId, String sessionId) {
        return cartService.getCartItemCount(userId, sessionId);
    }

    @Override
    public void mergeCartItems(String username, List<CartItemDTO> cartItems) {
        cartService.mergeCartItems(username, cartItems);
    }

    @Override
    public List<CartItemDTO> getUserCart(String username) {
        return cartService.getUserCart(username);
    }
}
