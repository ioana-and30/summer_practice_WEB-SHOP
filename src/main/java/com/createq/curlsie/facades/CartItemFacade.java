package com.createq.curlsie.facades;

import com.createq.curlsie.dto.CartItemDTO;

import java.util.List;

public interface CartItemFacade {

    public List<CartItemDTO> getAll();
    public CartItemDTO getById(Long id);
    public List<CartItemDTO> getByCartId(Long cartId);
}
