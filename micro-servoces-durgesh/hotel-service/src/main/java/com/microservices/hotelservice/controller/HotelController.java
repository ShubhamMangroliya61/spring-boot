package com.microservices.hotelservice.controller;

import com.microservices.hotelservice.entity.Hotel;
import com.microservices.hotelservice.service.HotelService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<?> addHotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<?> getHotelById(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.FOUND).body(hotelService.GetById(hotelId));
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(HttpStatus.FOUND).body(hotelService.getAll());
    }
}
