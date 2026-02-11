package com.taskapp.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taskapp.userservice.entity.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class UserRequestDTO {

    @NotBlank(message = "Username cannot be empty")
    String username;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email cannot be empty")
    String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    String password;
    @NotNull(message = "Role is required")
    UserRole role;
}