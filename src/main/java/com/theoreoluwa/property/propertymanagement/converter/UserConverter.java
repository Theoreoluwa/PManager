package com.theoreoluwa.property.propertymanagement.converter;

import com.theoreoluwa.property.propertymanagement.DTO.UserDTO;
import com.theoreoluwa.property.propertymanagement.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserEntity convertDTOToEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();

        userEntity.setOwnerName(userDTO.getOwnerName());
        userEntity.setOwnerEmail(userDTO.getOwnerEmail());
        userEntity.setPhone(userDTO.getPhone());
        userEntity.setPassword(userDTO.getPassword());
        return userEntity;
    }

    public UserDTO convertEntityToDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(userEntity.getId());
        userDTO.setOwnerName(userEntity.getOwnerName());
        userDTO.setOwnerEmail(userEntity.getOwnerEmail());
        userDTO.setPhone(userEntity.getPhone());
        return userDTO;
    }
}
