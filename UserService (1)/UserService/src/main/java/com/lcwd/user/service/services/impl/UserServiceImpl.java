package com.lcwd.user.service.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;




@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate; 
	
	@Autowired
	private HotelService hotelService;
	
	
	//We can easily create Logger in Java by using the getLogger() method. 
	//It finds a logger if a logger is already defined and returns the same logger, 
	//else creates a new logger for a named subsystem.
	//private Logger logger=LogManager.getLogger(UserServiceImpl.class);

	
	@Override
	public User saveUser(User user) {
		// generate unique userId
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		
		return userRepository.findAll(); 
	}

	@Override
	public User getUser(String userId) {
		
		User user= userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! :"+userId));
		// fetch rating of the above user from RATING SERVICE
		// http://localhost:8080/ratings/users/b06879d5-174e-40a7-8075-80a1dd14e220
//		
		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(),Rating[].class);
//		logger.info("{}",ratingsOfUser );
		
		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList(); 
		
		List<Rating> ratingList = ratings.stream().map(rating -> {
			
			//api call to hotel service to get the hotel
			//http://localhost:8085/hotels/80848ddd-d7c2-4f45-ac0c-3ce3b763a169
			
			//ResponseEntity<Hotel>  forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(),Hotel.class);
			//Hotel hotel=forEntity.getBody();
			Hotel hotel=hotelService.getHotel(rating.getHotelId());
			//logger.info("response status code: {}",forEntity.getStatusCode());
			
			//set the hotel to rating
			rating.setHotel(hotel);
			
			//return the rating
			return rating;
		}).collect(Collectors.toList());
//		
		
		
//		user.setRatings(ratingList);
		
		return user;
	}

}
