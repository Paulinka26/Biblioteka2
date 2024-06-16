package com.example.biblioteka2.user;

import com.example.biblioteka2.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

/**
 * Klasa reprezentująca użytkownika systemu bibliotecznego.
 */
@Entity
@Table(name = "users", schema = "library")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId; // Identyfikator użytkownika

    @Basic
    @Column(name = "email")
    private String email; // Adres email użytkownika

    @Basic
    @Column(name = "full_name")
    private String fullName; // Pełne imię i nazwisko użytkownika

    @Column(name = "username", unique = true, nullable = false)
    @Basic
    private String username; // Nazwa użytkownika (unikalna)

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;


    public long getUserId() {
        return userId;
    }


    public void setUserId(long userId) {
        this.userId = userId;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public UserRole getRole() {
        return role;
    }



    public void setRole(UserRole role) {
        this.role = role;
    }
}
