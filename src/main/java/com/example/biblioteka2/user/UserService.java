package com.example.biblioteka2.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * Serwis obsługujący operacje na użytkownikach w systemie.
 */
@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    /**
     * Konstruktor serwisu użytkowników.
     * @param userRepository repozytorium użytkowników
     * @param passwordEncoder enkoder hasła użytkownika
     */
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Pobiera wszystkich użytkowników z systemu.
     * @return lista wszystkich użytkowników
     */
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * Pobiera pojedynczego użytkownika na podstawie jego identyfikatora.
     * @param userId identyfikator użytkownika
     * @return użytkownik o podanym identyfikatorze
     * @throws RuntimeException jeśli użytkownik nie zostanie znaleziony
     */
    public User getOne(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * Tworzy nowego użytkownika w systemie.
     * @param user dane nowego użytkownika
     * @return nowo utworzony użytkownik
     */
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Usuwa użytkownika z systemu na podstawie jego identyfikatora.
     * @param userId identyfikator użytkownika do usunięcia
     * @throws RuntimeException jeśli użytkownik nie zostanie znaleziony
     */
    public void delete(long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(userId);
    }

    /**
     * Sprawdza, czy istnieje użytkownik o podanej nazwie użytkownika.
     * @param username nazwa użytkownika do sprawdzenia
     * @return true, jeśli użytkownik o podanej nazwie istnieje; w przeciwnym razie false
     */
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
