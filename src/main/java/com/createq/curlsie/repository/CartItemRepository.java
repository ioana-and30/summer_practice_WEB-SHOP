package com.createq.curlsie.repository;

import com.createq.curlsie.model.CartItemModel;
import com.createq.curlsie.model.CartModel;
import com.createq.curlsie.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItemModel, Long> {
    List<CartItemModel> findByCart(CartModel cart);
    Optional<CartItemModel> findByCartIdAndProductId(Long cartId, Long productId);
}
