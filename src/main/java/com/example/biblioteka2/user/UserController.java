package com.example.biblioteka2.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{user_id}")
    public User getOne(@PathVariable long user_id) {
        return userService.getOne(user_id);
    }
    @PostMapping
    public ResponseEntity<User> create (@RequestBody User user){
        var newUser = userService.create(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> delete(@PathVariable long user_id) {
        userService.delete(user_id);
        return ResponseEntity.noContent().build();
    }
}
