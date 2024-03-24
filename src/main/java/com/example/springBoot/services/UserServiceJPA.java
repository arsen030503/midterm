package com.example.springBoot.services;

import com.example.springBoot.dto.UserDTO;
import com.example.springBoot.entities.User;
import com.example.springBoot.mappers.UserMapper;
import com.example.springBoot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceJPA implements UserService {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return userMapper.userToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        user = userRepository.save(user);
        return userMapper.userToUserDTO(user);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            return null;
        }
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());
        existingUser = userRepository.save(existingUser);
        return userMapper.userToUserDTO(existingUser);
    }

    @Override
    public UserDTO partiallyUpdateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            return null;
        }

        // Apply partial updates
        if (userDTO.getUsername() != null) {
            existingUser.setUsername(userDTO.getUsername());
        }
        if (userDTO.getEmail() != null) {
            existingUser.setEmail(userDTO.getEmail());
        }
        // Add more fields to update as needed

        existingUser = userRepository.save(existingUser);
        return userMapper.userToUserDTO(existingUser);
    }


    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
