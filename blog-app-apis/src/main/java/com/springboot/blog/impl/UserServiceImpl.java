package com.springboot.blog.impl;

import com.springboot.blog.entities.User;
import com.springboot.blog.exceptions.ResourceNotFountException;
import com.springboot.blog.exceptions.UserAlreadyExistsException;
import com.springboot.blog.payloads.UserDto;
import com.springboot.blog.repositories.UserRepository;
import com.springboot.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        User newUser = this.userDtoToUser(userDto);
        User savedNewUser = userRepository.save(newUser);
        return this.userToUserDto(savedNewUser);
    }

    @Override
    public UserDto updateUser(Integer userId, UserDto userDto) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFountException("User", "Id", userId));

        if(userDto.getName() != null)user.setName(userDto.getName());
        if(userDto.getEmail() != null)user.setEmail(userDto.getEmail());
        if(userDto.getPassword() != null)user.setPassword(userDto.getPassword());
        if(userDto.getAbout() != null)user.setAbout(userDto.getAbout());

        User updatedUser = userRepository.save(user);

        return this.userToUserDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFountException("User", "Id", userId));

        return this.userToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> allUsers = userRepository.findAll();

        return allUsers
                .stream()
                .map(this::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFountException("User", "Id", userId));

        userRepository.delete(user);
    }

    public User userDtoToUser(UserDto userDto){
        //        User user = new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return this.modelMapper.map(userDto, User.class);
    }

    public UserDto userToUserDto(User user){
//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return this.modelMapper.map(user, UserDto.class);
    }
}
