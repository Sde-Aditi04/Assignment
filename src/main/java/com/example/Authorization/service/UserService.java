package com.example.Authorization.service;


import com.example.Authorization.dto.UserDTO;
import com.example.Authorization.model.User;

public interface UserService {
    User createUser(UserDTO userDTO);
    User getUserById(Long id);
    User updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
}

