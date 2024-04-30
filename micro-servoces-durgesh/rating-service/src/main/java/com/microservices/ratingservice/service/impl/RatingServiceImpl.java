package com.microservices.ratingservice.service.impl;

import com.microservices.ratingservice.entities.Rating;
import com.microservices.ratingservice.exception.ResourceNotFoundException;
import com.microservices.ratingservice.repository.RatingRepository;
import com.microservices.ratingservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    private RatingRepository ratingRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Rating create(Rating rating) {
        String ratingId = UUID.randomUUID().toString();
        rating.setRatingId(ratingId);
        return ratingRepository.save((rating));
    }

    @Override
    public List<Rating> getAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating getById(String ratingId) {
        return ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating not found with id: "+ ratingId));
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
