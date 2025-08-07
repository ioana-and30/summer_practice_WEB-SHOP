package com.createq.curlsie.repository;

import com.createq.curlsie.dto.UserDTO;
import com.createq.curlsie.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByUsername(String username);
    int countByEmail(String email);
    int countByUsername(String username);
}
