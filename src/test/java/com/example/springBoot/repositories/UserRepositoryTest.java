package com.example.springBoot.repositories;

import com.example.springBoot.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("john_doe");
        user.setEmail("john@example.com");

        User savedUser = userRepository.save(user);
        assertNotNull(savedUser.getId());
        assertEquals("john_doe", savedUser.getUsername());
        assertEquals("john@example.com", savedUser.getEmail());
    }

    @Test
    public void testFindUserById() {
        User user = new User();
        user.setUsername("jane_doe");
        user.setEmail("jane@example.com");

        User savedUser = userRepository.save(user);
        Optional<User> optionalUser = userRepository.findById(savedUser.getId());
        assertTrue(optionalUser.isPresent());
        assertEquals("jane_doe", optionalUser.get().getUsername());
        assertEquals("jane@example.com", optionalUser.get().getEmail());
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setUsername("alice_smith");
        user.setEmail("alice@example.com");

        User savedUser = userRepository.save(user);
        savedUser.setEmail("alice.smith@example.com");

        User updatedUser = userRepository.save(savedUser);
        assertEquals(savedUser.getId(), updatedUser.getId());
        assertEquals("alice_smith", updatedUser.getUsername());
        assertEquals("alice.smith@example.com", updatedUser.getEmail());
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setUsername("bob_jones");
        user.setEmail("bob@example.com");

        User savedUser = userRepository.save(user);
        userRepository.delete(savedUser);

        Optional<User> optionalUser = userRepository.findById(savedUser.getId());
        assertFalse(optionalUser.isPresent());
    }
}
