package com.microservices.userservice.controller;

import com.microservices.userservice.entities.User;
import com.microservices.userservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user){
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable String userId){
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUsers(){
        List<User> listOfUsers = userService.getAllUsers();
        return ResponseEntity.ok(listOfUsers);
    }
}
