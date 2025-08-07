package com.createq.curlsie.service;

import com.createq.curlsie.dto.UserDTO;
import com.createq.curlsie.exceptions.ResourceNotFoundException;
import com.createq.curlsie.model.UserModel;

import java.util.List;


public interface UserService {

    public List<UserModel> getAll() throws ResourceNotFoundException;
    void registerNewUser(UserDTO userDTO);
    void createNewUser(String Username, String Password, UserModel.Role Role);
    UserModel findByUsername(String username);
    UserModel getUserById(Long id);
}
