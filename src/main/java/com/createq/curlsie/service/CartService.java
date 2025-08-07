package com.createq.curlsie.service;

import com.createq.curlsie.dto.CartDTO;
import com.createq.curlsie.dto.CartItemDTO;

import java.util.List;

public interface CartService {
    CartDTO getCartByUser(Long userId);

    CartDTO getCart(Long userId, String sessionId);

    CartDTO addItemToCart(Long userId, Long productId, String sessionId,int quantity);

    CartDTO updateItemQuantity(Long userId, String sessionId,Long productId, int quantity);

    CartDTO removeItemFromCart(Long userId,String sessionId, Long productId);

    int getCartItemCount(Long userId, String sessionId);

    void mergeCartItems(String username, List<CartItemDTO> cartItems);

    List<CartItemDTO> getUserCart(String username);

}
