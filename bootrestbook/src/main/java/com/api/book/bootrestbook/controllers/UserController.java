package com.api.book.bootrestbook.controllers;

import com.api.book.bootrestbook.entities.User;
import com.api.book.bootrestbook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody User user) {
    User existingUser = userService.getUserByEmail(user.getEmail());
    if (existingUser != null) {
      return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body("User with this email already exists");
    }
    User newUser = userService.addUser(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<?> getUserById(@PathVariable("userId") int userId) {
    User userById = userService.getUserById(userId);
    if (userById == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
    return ResponseEntity.ok(userById);
  }

  @PostMapping("/login")
  public ResponseEntity<?> loginUser(@RequestBody User user) {
    User userByEmail = userService.getUserByEmail(user.getEmail());
    if (userByEmail == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
    return ResponseEntity.ok(userByEmail);
  }
}
