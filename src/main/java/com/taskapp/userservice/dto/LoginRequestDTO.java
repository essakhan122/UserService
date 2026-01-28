package com.taskapp.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class LoginRequestDTO {
    @NotBlank(message = "Please enter a valid email")
    @NotBlank(message = "Email cannot be empty")
    String email;
    @NotBlank(message = "Password cannot be empty")
    String password;
}
