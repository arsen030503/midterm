package com.example.springBoot.mappers;

import com.example.springBoot.dto.UserDTO;
import com.example.springBoot.entities.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User userDTOToUser(UserDTO dto);

    UserDTO userToUserDTO(User user);
}