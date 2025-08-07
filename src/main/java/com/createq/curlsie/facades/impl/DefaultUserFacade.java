package com.createq.curlsie.facades.impl;

import com.createq.curlsie.converter.UserConverter;
import com.createq.curlsie.dto.UserDTO;
import com.createq.curlsie.exceptions.ResourceNotFoundException;
import com.createq.curlsie.facades.UserFacade;
import com.createq.curlsie.model.UserModel;
import com.createq.curlsie.repository.UserRepository;
import com.createq.curlsie.service.UserService;

import java.util.List;

public class DefaultUserFacade implements UserFacade {

    private final UserService userService;
    private final UserConverter userConverter;
    private final UserRepository userRepository;

    public DefaultUserFacade(UserService userService, UserConverter userConverter, UserRepository userRepository) {
        this.userService = userService;
        this.userConverter = userConverter;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAll() throws ResourceNotFoundException {
        return userConverter.convertAllToDTO(userService.getAll());
    }

    @Override
    public UserDTO saveOrUpdate(UserDTO userDTO) {
        UserModel user = userConverter.convertToModel(userDTO);
        return userConverter.convertToDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateRole(Long userId, String role) {
        UserModel user = userRepository.findById(userId).orElseThrow();
        user.setRole(UserModel.Role.valueOf(role));
        userRepository.save(user);
    }
}
