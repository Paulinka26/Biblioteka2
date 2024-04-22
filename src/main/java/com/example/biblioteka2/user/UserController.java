package com.example.biblioteka2.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Kontroler obsługujący żądania dotyczące użytkowników w systemie.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    /**
     * Konstruktor kontrolera użytkowników.
     * @param userService serwis użytkowników
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Wyjątek sygnalizujący, że użytkownik nie został znaleziony.
     */
    public class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    /**
     * Obsługuje żądanie pobrania wszystkich użytkowników.
     * @return lista wszystkich użytkowników
     */
    @GetMapping
    public List<User> getAllUsers() {
        try {
            return userService.getAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving users", e);
        }
    }

    /**
     * Obsługuje żądanie pobrania pojedynczego użytkownika na podstawie jego identyfikatora.
     * @param userId identyfikator użytkownika
     * @return odpowiedź HTTP z użytkownikiem lub informacją o błędzie
     */
    @GetMapping("/{userId}")
    public ResponseEntity<?> getOne(@PathVariable long userId) {
        try {
            User user = userService.getOne(userId);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with id: " + userId);
        }
    }

    /**
     * Obsługuje żądanie utworzenia nowego użytkownika.
     * @param user dane nowego użytkownika
     * @return odpowiedź HTTP z nowo utworzonym użytkownikiem lub informacją o błędzie
     */
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        try {
            if (userService.existsByUsername(user.getUsername())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this username already exists");
            }
            User newUser = userService.create(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating user", e);
        }
    }

    /**
     * Obsługuje żądanie usunięcia użytkownika na podstawie jego identyfikatora.
     * @param userId identyfikator użytkownika do usunięcia
     * @return odpowiedź HTTP z informacją o powodzeniu operacji usuwania lub informacją o błędzie
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable long userId) {
        try {
            userService.delete(userId);
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting user", e);
        }
    }
}
