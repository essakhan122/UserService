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

    @Column(nullable = false, unique = true)
    private  String email;


    @Column(nullable = false)
    private  String username;


    @Column(nullable = false, length =60)
    private  String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role = UserRole.DEV;

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
    public String getRole() {
        return role.name();
    }



    public void setPassword(String password)
    {
        this.password = password;
    }
    public void setEmail(String email){this.email =email;}
    public void setUsername(String username){this.username=username;}
    public void setRole(UserRole role) {
        this.role = role;
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
