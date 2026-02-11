package com.taskapp.userservice.repository;

import com.taskapp.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> getByEmail(String email);
}
