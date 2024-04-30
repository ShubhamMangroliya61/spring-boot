package com.microservices.ratingservice.service;

import com.microservices.ratingservice.entities.Rating;

import java.util.List;

public interface RatingService {
    Rating create(Rating rating);
    List<Rating> getAll();
    Rating getById(String ratingId);
    List<Rating> getRatingByUserId(String userId);
    List<Rating> getRatingByHotelId(String hotelId);
}
