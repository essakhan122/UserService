package com.taskapp.userservice.controller;
import com.taskapp.userservice.dto.LoginRequestDTO;
import com.taskapp.userservice.dto.LoginResponseDTO;
import com.taskapp.userservice.dto.UserRequestDTO;
import com.taskapp.userservice.dto.UserResponseDTO;
import com.taskapp.userservice.response.*;
import com.taskapp.userservice.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    UserController(UserService userService)
    {
        this.userService = userService;
    }
    @PostMapping("/create_user")
    public ResponseEntity<ApiResponse<Void>> createUser(@Valid @RequestBody UserRequestDTO user)
    {
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("User created successfully"));
    }
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
        public LoginResponseDTO login (@Valid @RequestBody LoginRequestDTO loginRequestDTO)
        {
            return userService.login(loginRequestDTO);
        }
    @GetMapping("/getuser")
     @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO getUser(@RequestParam("userId")
                                       @NotNull(message = "User ID is required")
                                       Long userId)
    {
        return userService.getUser(userId);
    }
}
