package com.example.springBoot.dto;
import com.example.springBoot.entities.User;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;
}
