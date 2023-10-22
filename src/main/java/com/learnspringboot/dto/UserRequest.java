package com.learnspringboot.dto;

import com.learnspringboot.enums.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class UserRequest {
    private Integer id;

    @NotEmpty(message = "firstname shouldn't be empty")
    private String firstname;

    @NotEmpty(message = "lastname shouldn't be empty")
    private String lastname;

    @Email(message = "invalid email address")
    private String email;

    @NotEmpty(message = "password shouldn't be empty")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
