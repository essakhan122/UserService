package com.taskapp.userservice.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Please enter a valid Email")
    @NotBlank(message = "Email cannot be empty")
    @Column(nullable = false, unique = true)
    private  String email;

    @NotBlank(message = "Username cannot be empty")
    @Column(nullable = false)
    private  String username;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Column(nullable = false, length =60)
    private  String password;
    private Instant createdAt;
    private Instant updatedAt;

    public Long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword(){return password;}
    public String getUsername() {
        return username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }
}
