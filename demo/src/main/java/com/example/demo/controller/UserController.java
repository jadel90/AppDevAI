package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create a new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Edit a user (reset password)
    @PutMapping("/{id}/reset-password")
    public User resetPassword(@PathVariable Long id, @RequestParam String newPassword) {
        return userService.resetPassword(id, newPassword);
    }

    // Edit a user (toggle unlocked status)
    @PutMapping("/{id}/toggle-unlocked")
    public User toggleUnlocked(@PathVariable Long id) {
        return userService.toggleUnlocked(id);
    }

    // Delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
