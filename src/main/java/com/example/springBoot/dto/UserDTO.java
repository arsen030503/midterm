package com.example.springBoot.dto;
import com.example.springBoot.entities.User;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
    private Long id;
    private String username;
    private String email;
}
