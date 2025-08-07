package com.createq.curlsie.repository;

import com.createq.curlsie.model.CartModel;
import com.createq.curlsie.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartModel, Long> {

    Optional<CartModel> findByUserId(Long userId);
    Optional<CartModel> findBySessionId(String sessionId);
    Optional<CartModel> findByUser(UserModel user);

}
