package com.learnspringboot.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotEmpty(message = "firstname shouldn't be empty")
    private String firstname;

    @NotEmpty(message = "lastname shouldn't be empty")
    private String lastname;

    @Email(message = "invalid email address")
    private String email;

    @NotEmpty(message = "password shouldn't be empty")
    private String password;

}
