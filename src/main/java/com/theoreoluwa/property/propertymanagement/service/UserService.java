package com.theoreoluwa.property.propertymanagement.service;

import com.theoreoluwa.property.propertymanagement.DTO.UserDTO;

public interface UserService {

    UserDTO register(UserDTO userDTO);
    UserDTO login(String email, String password);
}


