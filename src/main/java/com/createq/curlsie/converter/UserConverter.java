package com.createq.curlsie.converter;

import com.createq.curlsie.dto.UserDTO;
import com.createq.curlsie.model.UserModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {

    public UserDTO convertToDTO(UserModel userModel) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userModel.getId());
        userDTO.setUsername(userModel.getUsername());
        userDTO.setFirstName(userModel.getFirstName());
        userDTO.setLastName(userModel.getLastName());
        userDTO.setEmail(userModel.getEmail());
        userDTO.setEnabled(userModel.isEnabled());
        userDTO.setRole(userModel.getRole());

        return userDTO;
    }

    public UserModel convertToModel(UserDTO userDTO) {
        UserModel userModel = new UserModel();
        userModel.setId(userDTO.getId());
        userModel.setUsername(userDTO.getUsername());
        userModel.setFirstName(userDTO.getFirstName());
        userModel.setLastName(userDTO.getLastName());
        userModel.setEmail(userDTO.getEmail());
        userModel.setEnabled(userDTO.isEnabled());
        userModel.setRole(userDTO.getRole());

        return userModel;
    }

    public List<UserDTO> convertAllToDTO(List<UserModel> userModels) {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (UserModel userModel : userModels) {
            userDTOS.add(convertToDTO(userModel));
        }

        return userDTOS;
    }

    public List<UserModel> convertAllToModel(List<UserDTO> userDTOS){
        List<UserModel> userModels =new ArrayList<>();
        for(UserDTO userDTO : userDTOS){
            userModels.add(convertToModel(userDTO));
        }

        return userModels;
    }
}
