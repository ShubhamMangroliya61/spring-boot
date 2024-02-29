package com.springboot.blog.impl;

import com.springboot.blog.entities.User;
import com.springboot.blog.exceptions.ResourceNotFountException;
import com.springboot.blog.payloads.UserDto;
import com.springboot.blog.repositories.UserRepository;
import com.springboot.blog.services.UserService;
import com.springboot.blog.utils.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
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
    public UserDto updateUser(Integer userId, UserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFountException("User", "Id", userId));

        if(userDto.getName() != null)user.setName(userDto.getName());
        if(userDto.getEmail() != null)user.setEmail(userDto.getEmail());
        if(userDto.getPassword() != null)user.setPassword(userDto.getPassword());
        if(userDto.getAbout() != null)user.setAbout(userDto.getAbout());

        User updatedUser = userRepository.save(user);
        UserDto updatedUserDto = UserHelper.userToUserDto(updatedUser);

        return updatedUserDto;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFountException("User", "Id", userId));

        return UserHelper.userToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> allUsers = userRepository.findAll();

        List<UserDto> allUsersDto = allUsers.stream().map(user -> UserHelper.userToUserDto(user)).collect(Collectors.toList());

        return allUsersDto;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFountException("User", "Id", userId));

        userRepository.delete(user);
    }
}
