package com.taskapp.userservice.service;
import com.taskapp.userservice.dto.UserRequestDTO;
import com.taskapp.userservice.entity.User;
import com.taskapp.userservice.exception.GenericRuntimeException;
import com.taskapp.userservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(UserRequestDTO userRequest) {
        User existingUser = userRepository.getByEmail(userRequest.getEmail());
        if (existingUser != null) {
            throw new GenericRuntimeException("Email already registered");
        }
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userRepository.save(user);
    }
}
