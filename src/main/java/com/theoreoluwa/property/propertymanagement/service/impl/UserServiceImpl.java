package com.theoreoluwa.property.propertymanagement.service.impl;

import com.theoreoluwa.property.propertymanagement.DTO.UserDTO;
import com.theoreoluwa.property.propertymanagement.converter.UserConverter;
import com.theoreoluwa.property.propertymanagement.entity.UserEntity;
import com.theoreoluwa.property.propertymanagement.repository.UserRepository;
import com.theoreoluwa.property.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO userDTO) {

        UserEntity userEntity = userConverter.convertDTOToEntity(userDTO);

        userEntity = userRepository.save(userEntity);
        userDTO = userConverter.convertEntityToDTO(userEntity);
        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {
        return null;
    }
}
