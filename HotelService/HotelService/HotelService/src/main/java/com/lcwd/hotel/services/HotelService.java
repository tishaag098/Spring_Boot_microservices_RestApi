package com.lcwd.hotel.services;

import java.util.List;

import com.lcwd.hotel.entities.Hotel;

public interface HotelService {
	
	//create
	
	Hotel create(Hotel hotel);
	
	//get all hotels
	List<Hotel> getAll();
	
	//get single
	Hotel get(String id);

}
