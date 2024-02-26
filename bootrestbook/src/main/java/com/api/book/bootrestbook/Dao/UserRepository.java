package com.api.book.bootrestbook.Dao;

import com.api.book.bootrestbook.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
  public User findById(int userId);

  public User findByEmail(String userEmail);
}
