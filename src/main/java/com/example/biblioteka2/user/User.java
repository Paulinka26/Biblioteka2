package com.example.biblioteka2.user;

import com.example.biblioteka2.UserRole;
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
    @Basic
    private String password; // Hasło użytkownika

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role; // Rola użytkownika w systemie

    /**
     * Metoda zwracająca identyfikator użytkownika.
     * @return identyfikator użytkownika
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Metoda ustawiająca identyfikator użytkownika.
     * @param userId nowy identyfikator użytkownika
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Metoda zwracająca adres email użytkownika.
     * @return adres email użytkownika
     */
    public String getEmail() {
        return email;
    }

    /**
     * Metoda ustawiająca adres email użytkownika.
     * @param email nowy adres email użytkownika
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Metoda zwracająca pełne imię i nazwisko użytkownika.
     * @return pełne imię i nazwisko użytkownika
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Metoda ustawiająca pełne imię i nazwisko użytkownika.
     * @param fullName nowe pełne imię i nazwisko użytkownika
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Metoda zwracająca nazwę użytkownika.
     * @return nazwa użytkownika
     */
    public String getUsername() {
        return username;
    }

    /**
     * Metoda ustawiająca nazwę użytkownika.
     * @param username nowa nazwa użytkownika
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Metoda zwracająca hasło użytkownika.
     * @return hasło użytkownika
     */
    public String getPassword() {
        return password;
    }

    /**
     * Metoda ustawiająca hasło użytkownika.
     * @param password nowe hasło użytkownika
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Metoda zwracająca rolę użytkownika.
     * @return rola użytkownika
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Metoda ustawiająca rolę użytkownika.
     * @param role nowa rola użytkownika
     */
    public void setRole(UserRole role) {
        this.role = role;
    }
}
