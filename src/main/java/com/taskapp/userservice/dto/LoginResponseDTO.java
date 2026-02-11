package com.taskapp.userservice.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    Long userId;
    String username;
    String email;
//    String token;
}