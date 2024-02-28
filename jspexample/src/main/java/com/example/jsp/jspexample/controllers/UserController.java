package com.example.jsp.jspexample.controllers;

import com.example.jsp.jspexample.models.User;
import com.example.jsp.jspexample.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @RequestMapping(value = "/registerUser", method = RequestMethod.GET)
  public String registerUser() {
    return "register";
  }

  @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
  public String registerUser(
    @RequestParam String name,
    @RequestParam String email,
    @RequestParam String password
  ) {
    User newUser = new User();
    newUser.setName(name);
    newUser.setEmail(email);
    newUser.setPassword(password);
    userRepository.save(newUser);
    return "view-books";
  }
}
