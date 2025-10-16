package com.theoreoluwa.property.propertymanagement.service.impl;

import com.theoreoluwa.property.propertymanagement.DTO.UserDTO;
import com.theoreoluwa.property.propertymanagement.converter.UserConverter;
import com.theoreoluwa.property.propertymanagement.entity.UserEntity;
import com.theoreoluwa.property.propertymanagement.exception.BusinessException;
import com.theoreoluwa.property.propertymanagement.exception.ErrorModel;
import com.theoreoluwa.property.propertymanagement.repository.UserRepository;
import com.theoreoluwa.property.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO userDTO) {

        Optional<UserEntity> optionalUserEntity = userRepository.findByOwnerEmail(userDTO.getOwnerEmail());
        if (optionalUserEntity.isPresent()) {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setErrorCode("User already exists!");
            errorModel.setErrorMessage("User already exists, try again with different details");
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList);
        }
        UserEntity userEntity = userConverter.convertDTOToEntity(userDTO);
        userEntity = userRepository.save(userEntity);
        userDTO = userConverter.convertEntityToDTO(userEntity);
        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {
        UserDTO userDTO;
        Optional<UserEntity> optionalUserEntity = userRepository.findByOwnerEmailAndPassword(email, password);
        if (optionalUserEntity.isPresent()) {
            userDTO = userConverter.convertEntityToDTO(optionalUserEntity.get());
        }else {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setErrorCode("INVALID_LOGIN");
            errorModel.setErrorMessage("Invalid email or password");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }
        return userDTO;
    }
}
