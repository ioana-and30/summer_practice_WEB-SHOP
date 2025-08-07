package com.createq.curlsie.facades;

import com.createq.curlsie.dto.UserDTO;
import com.createq.curlsie.model.UserModel;

import java.util.List;

public interface UserFacade {

    public List<UserDTO> getAll();
    public UserDTO saveOrUpdate(UserDTO userDTO);
    public void deleteUser(Long id);
    public void updateRole(Long userId, String role);
}
