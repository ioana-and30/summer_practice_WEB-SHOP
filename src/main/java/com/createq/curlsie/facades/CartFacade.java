package com.createq.curlsie.facades;

import com.createq.curlsie.dto.CartDTO;
import com.createq.curlsie.dto.CartItemDTO;
import com.createq.curlsie.model.CartItemModel;

import java.util.List;

public interface CartFacade {

    CartDTO getCartByUser(Long userId);

    CartDTO getCart(Long userId, String sessionId);

    void addItemToCart(Long userId, Long productId,String sessionId, int quantity);

    void updateItemQuantity(Long userId, String sessionId, Long productId, int quantity);

    void removeItemFromCart(Long userId,String sessionId, Long productId);

    int getCartItemCount(Long userId, String sessionId);

    void mergeCartItems(String username, List<CartItemDTO> cartItems);

    List<CartItemDTO> getUserCart(String username);
}
