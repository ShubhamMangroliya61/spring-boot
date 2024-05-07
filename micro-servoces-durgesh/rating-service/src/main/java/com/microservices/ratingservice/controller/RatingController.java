package com.microservices.ratingservice.controller;

import com.microservices.ratingservice.entities.Rating;
import com.microservices.ratingservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(HttpStatus.FOUND).body(ratingService.getAll());
    }

    @GetMapping("/byId/{ratingId}")
    public ResponseEntity<?> getByRatingId(@PathVariable String ratingId){
        return ResponseEntity.status(HttpStatus.FOUND).body(ratingService.getById(ratingId));
    }

    @GetMapping("/byUserId/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.FOUND).body(ratingService.getRatingByUserId(userId));
    }

    @GetMapping("/byHotelId/{hotelId}")
    public ResponseEntity<?> getByHotelId(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.FOUND).body(ratingService.getRatingByHotelId(hotelId));
    }
}
