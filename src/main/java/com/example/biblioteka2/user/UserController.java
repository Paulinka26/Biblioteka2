package com.example.biblioteka2.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    public class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }


    @GetMapping
    public List<User> getAllUsers() {
        try {
            return userService.getAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving users", e);
        }
    }


    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserId(@PathVariable long userId) {
        try {
            User user = userService.getUserId(userId);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with id: " + userId);
        }
    }


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
