package com.example.springBoot.mappers;

import com.example.springBoot.dto.UserDTO;
import com.example.springBoot.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(target = "tasks", ignore = true)
    User userDTOToUser(UserDTO dto);

    UserDTO userToUserDTO(User user);
}