package com.microservices.userservice.servicesImpl;

import com.microservices.userservice.entities.Hotel;
import com.microservices.userservice.entities.Rating;
import com.microservices.userservice.entities.User;
import com.microservices.userservice.exceptions.ResourceNotFoundException;
import com.microservices.userservice.repositories.UserRepository;
import com.microservices.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;
    private RestTemplate restTemplate;

    @Autowired
    public UserServiceImp(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        allUsers.forEach(user -> {
            Rating[] ratings =  restTemplate.getForEntity("http://RATING-SERVICE/ratings/byUserId/"+user.getUserId(), Rating[].class).getBody();
            assert ratings != null;
            List<Rating> ratingsOfUser = Arrays.stream(ratings).collect(Collectors.toList());
            ratingsOfUser.forEach(rating -> {
                Hotel hotel = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class).getBody();
                rating.setHotel(hotel);
            });
            user.setRatings(ratingsOfUser);
        });
        return allUsers;
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given Id"));
        Rating[] ratings = restTemplate.getForEntity("http://RATING-SERVICE/ratings/byUserId/"+userId, Rating[].class).getBody();
        assert ratings != null;
        List<Rating> ratingsOfUser = Arrays.stream(ratings).collect(Collectors.toList());
        ratingsOfUser.forEach(rating -> {
            Hotel hotel = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class).getBody();
            rating.setHotel(hotel);
        });
        user.setRatings(ratingsOfUser);
        return user;
    }
}
