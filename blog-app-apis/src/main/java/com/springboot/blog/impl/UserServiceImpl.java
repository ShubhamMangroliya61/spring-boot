package com.springboot.blog.impl;

import com.springboot.blog.entities.User;
import com.springboot.blog.payloads.UserDto;
import com.springboot.blog.repositories.UserRepository;
import com.springboot.blog.services.UserService;
import com.springboot.blog.utils.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDto) {
        User newUser = UserHelper.userDtoToUser(userDto);
        User savedNewUser = userRepository.save(newUser);
        return UserHelper.userToUserDto(savedNewUser);
    }

    @Override
    public UserDto updateUser(Integer userId, UserDto user) {
        return null;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }

    @Override
    public void deleteUser(Integer userId) {

    }
}
