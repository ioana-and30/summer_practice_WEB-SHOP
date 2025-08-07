package com.createq.curlsie.service.impl;

import com.createq.curlsie.dto.UserDTO;
import com.createq.curlsie.exceptions.ResourceNotFoundException;
import com.createq.curlsie.model.UserModel;
import com.createq.curlsie.repository.UserRepository;
import com.createq.curlsie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DefaultUserService(UserRepository userRepository, PasswordEncoder passwordEncoder, DataSource dataSource) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserModel> getAll() throws ResourceNotFoundException {
        return userRepository.findAll();
    }

    @Override
    public void registerNewUser(UserDTO userDTO) {

        System.out.println(">> Register User: " + userDTO.getUsername());

        if(userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Utilizatorul deja exista!");
        }

        UserModel userModel = new UserModel();
        userModel.setUsername(userDTO.getUsername());
        userModel.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        if (userDTO.getRole() == null) {
            userModel.setRole(UserModel.Role.ROLE_USER);
        } else {
            userModel.setRole(userDTO.getRole());
        }

        userModel.setFirstName(userDTO.getFirstName());
        userModel.setLastName(userDTO.getLastName());
        userModel.setEmail(userDTO.getEmail());

        userModel.setEnabled(true);
        userRepository.save(userModel);
        System.out.println("Salvare în baza de date realizată!");

    }

    public int countUsersByEmail(String email) {
        return userRepository.countByEmail(email);
    }

    public int countUsersByUsername(String username) {
        return userRepository.countByUsername(username);
    }

    @Override
    public void createNewUser(String Username, String Password, UserModel.Role role) {
        UserModel userModel = new UserModel();
        userModel.setUsername(Username);
        userModel.setPassword(passwordEncoder.encode(Password));
        userModel.setRole(role);
        userRepository.save(userModel);
    }

    @Override
    public UserModel findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public UserModel getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
