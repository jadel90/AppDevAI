package com.example.demo.unit_test;



import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testuser");
        testUser.setPassword("password123");
        testUser.setRole("USER");
        testUser.setUnlocked(true);
    }

    @Test
    void createUser_ShouldReturnSavedUser() {
        when(userRepository.save(testUser)).thenReturn(testUser);

        User createdUser = userService.createUser(testUser);

        assertNotNull(createdUser);
        assertEquals("testuser", createdUser.getUsername());
        verify(userRepository, times(1)).save(testUser);
    }

    @Test
    void getUserById_ShouldReturnUser_WhenExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));

        User foundUser = userService.getUserById(1L);

        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void getUserById_ShouldThrowException_WhenNotFound() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.getUserById(2L));

        assertEquals("User not found with ID: 2", exception.getMessage());
        verify(userRepository, times(1)).findById(2L);
    }

    @Test
    void getAllUsers_ShouldReturnListOfUsers() {
        when(userRepository.findAll()).thenReturn(List.of(testUser));

        List<User> users = userService.getAllUsers();

        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals("testuser", users.get(0).getUsername());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void updateUser_ShouldUpdateAndReturnUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(userRepository.save(testUser)).thenReturn(testUser);

        testUser.setPassword("newpassword");
        User updatedUser = userService.updateUser(1L, testUser);

        assertNotNull(updatedUser);
        assertEquals("newpassword", updatedUser.getPassword());
        verify(userRepository, times(1)).save(testUser);
    }

    @Test
    void deleteUser_ShouldRemoveUser_WhenExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        doNothing().when(userRepository).delete(testUser);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).delete(testUser);
    }

    @Test
    void deleteUser_ShouldThrowException_WhenNotFound() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.deleteUser(2L));

        assertEquals("User not found with ID: 2", exception.getMessage());
        verify(userRepository, times(1)).findById(2L);
    }
}
