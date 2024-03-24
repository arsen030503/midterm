package com.example.springBoot.services;

import com.example.springBoot.dto.UserDTO;
import com.example.springBoot.entities.User;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(Long userId);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(Long userId, UserDTO userDTO);
    void deleteUser(Long userId);
    UserDTO partiallyUpdateUser(Long userId, UserDTO userDTO);
}
