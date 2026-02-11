package com.taskapp.userservice.dto;
import com.taskapp.userservice.entity.UserRole;
import lombok.Data;
@Data
public class UserResponseDTO {
String email;
String userName;
Long userId;
String role;
//String token;
}
