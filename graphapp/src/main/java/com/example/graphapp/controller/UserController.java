package com.example.graphapp.controller;

import com.example.graphapp.model.User;
import com.example.graphapp.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/test")
    public String testConnection() {
        return userService.testConnection();
    }

    @PostMapping("/users")
    public String createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/users")
    public String getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public String updateUser(
            @PathVariable String id,
            @RequestBody User user) {

        user.setId(id);

        return userService.updateUser(user);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }
}