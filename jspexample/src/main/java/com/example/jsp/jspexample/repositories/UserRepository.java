package com.example.jsp.jspexample.repositories;

import com.example.jsp.jspexample.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends CrudRepository<User, Integer> {}
