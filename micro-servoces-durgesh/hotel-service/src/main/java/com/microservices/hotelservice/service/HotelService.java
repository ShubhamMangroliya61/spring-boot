package com.microservices.hotelservice.service;


import com.microservices.hotelservice.entity.Hotel;

import java.util.List;

public interface HotelService {
    Hotel create(Hotel hotel);
    List<Hotel> getAll();
    Hotel GetById(String hotelId);
}
