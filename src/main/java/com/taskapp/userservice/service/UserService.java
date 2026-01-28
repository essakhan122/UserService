package com.taskapp.userservice.service;
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

    public void createUser(User user) {
        User existingUser = userRepository.getByEmail(user.getEmail());
        if (existingUser != null) {
            throw new GenericRuntimeException("Email already registered");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
