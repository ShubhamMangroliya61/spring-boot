package com.api.book.bootrestbook.services;

import com.api.book.bootrestbook.Dao.UserRepository;
import com.api.book.bootrestbook.entities.User;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  // Add new user
  public User addUser(User user) {
    User savedUser = userRepository.save(user);
    return savedUser;
  }

  //Get user by Id
  public User getUserById(int userId) {
    User user = userRepository.findById(userId);
    return user;
  }

  //Get user ny Email
  public User getUserByEmail(String userEmail) {
    User user = userRepository.findByEmail(userEmail);
    return user;
  }

  //Get all users
  public ArrayList<User> getAllUsers() {
    ArrayList<User> listOfUsers = (ArrayList<User>) userRepository.findAll();

    return listOfUsers;
  }
}
