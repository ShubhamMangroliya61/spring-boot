package com.springboot.blog.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    String name;
    String email;

    public UserAlreadyExistsException(String name, String email) {
        super(String.format("User already exists with name: %s or with email: %s", name, email));
        this.name = name;
        this.email = email;
    }
}
