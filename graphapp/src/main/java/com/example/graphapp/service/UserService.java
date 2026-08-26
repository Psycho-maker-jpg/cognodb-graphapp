package com.example.graphapp.service;

import com.example.graphapp.model.User;
import com.example.graphapp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String testConnection() {
        return userRepository.testConnection();
    }

    public String createUser(User user) {
        return userRepository.createUser(user);
    }

    public String getAllUsers() {
        return userRepository.getAllUsers();
    }

    public String getUserById(String id) {
        return userRepository.getUserById(id);
    }

    public String updateUser(User user) {
        return userRepository.updateUser(user);
    }

    public String deleteUser(String id) {
        return userRepository.deleteUser(id);
    }
}