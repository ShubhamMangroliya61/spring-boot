package com.springboot.blog.utils;

import com.springboot.blog.entities.User;
import com.springboot.blog.payloads.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UserHelper {

    @Autowired
    private static ModelMapper modelMapper;
    public static User userDtoToUser(UserDto userDto){
        //        User user = new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return modelMapper.map(userDto, User.class);
    }

    public static UserDto userToUserDto(User user){
//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return modelMapper.map(user, UserDto.class);
    }
}
