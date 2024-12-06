package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User resetPassword(Long id, String newPassword) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(newPassword);
        return userRepository.save(user);
    }

    public User toggleUnlocked(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setUnlocked(!user.isUnlocked());
        return userRepository.save(user);
    }

    public abstract User getUserById(Long id);

    public abstract List<User> getAllUsers();

    public abstract User updateUser(Long id, User userDetails);

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
