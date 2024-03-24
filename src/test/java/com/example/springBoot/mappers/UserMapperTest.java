package com.example.springBoot.mappers;

import com.example.springBoot.dto.UserDTO;
import com.example.springBoot.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserDtoToUser() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("John Doe");
        userDTO.setEmail("john.doe@example.com");

        // When
        User user = userMapper.userDTOToUser(userDTO);

        // Then
        assertNotNull(user);
        assertEquals("John Doe", user.getUsername());
        assertEquals("john.doe@example.com", user.getEmail());
    }

    @Test
    public void testUserToUserDto() {
        // Given
        User user = new User();
        user.setUsername("Jane Smith");
        user.setEmail("jane.smith@example.com");

        // When
        UserDTO userDTO = userMapper.userToUserDTO(user);

        // Then
        assertNotNull(userDTO);
        assertEquals("Jane Smith", userDTO.getUsername());
        assertEquals("jane.smith@example.com", userDTO.getEmail());
    }
}