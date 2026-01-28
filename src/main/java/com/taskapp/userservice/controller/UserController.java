package com.taskapp.userservice.controller;
import com.taskapp.userservice.entity.User;
import com.taskapp.userservice.response.ApiResponse;
import com.taskapp.userservice.service.UserService;
import jakarta.validation.Valid;
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
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse createUser(@Valid @RequestBody User user)
        {
         userService.createUser(user);
         return new ApiResponse(true,"User created successfully");
        }
}
