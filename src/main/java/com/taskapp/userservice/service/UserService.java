package com.taskapp.userservice.service;
import com.taskapp.userservice.dto.LoginRequestDTO;
import com.taskapp.userservice.dto.LoginResponseDTO;
import com.taskapp.userservice.dto.UserResponseDTO;
import com.taskapp.userservice.dto.UserRequestDTO;
import com.taskapp.userservice.entity.User;
import com.taskapp.userservice.exception.GenericRuntimeException;
import com.taskapp.userservice.exception.InvalidCredentialsException;
import com.taskapp.userservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(UserRequestDTO userRequest) {
        Optional<User> existingUser = userRepository.getByEmail(userRequest.getEmail());
        if (existingUser.isPresent()){
            throw new GenericRuntimeException("Email already registered");
        }
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(userRequest.getRole());
        userRepository.save(user);
    }
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO)
    {
        User user = userRepository.getByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid Credentials")
                );
        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid Credentials");
        }
//        String token = jwtService.generateToken(user.getId(), user.getEmail());
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setEmail(user.getEmail());
        loginResponseDTO.setUsername(user.getUsername());
        loginResponseDTO.setUserId(user.getId());
//        loginResponseDTO.setToken(token);
        return loginResponseDTO;


    }
    public UserResponseDTO getUser(Long userId)
    {
        User user  = userRepository.findById(userId)
                .orElseThrow(() ->
            new InvalidCredentialsException("User details not found")
    );
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setUserName(user.getUsername());
        userResponseDTO.setUserId(user.getId());
        userResponseDTO.setRole(user.getRole());
        return userResponseDTO;
    }
}
