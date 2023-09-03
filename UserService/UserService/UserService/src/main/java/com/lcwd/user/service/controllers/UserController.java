package com.lcwd.user.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/users")
@Log4j2
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//create
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		User user1=userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	
	
	//single user get
	
	@GetMapping("/{userId}")
//	@CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId){
//		log.info("Get SIngle User handler: UserController");
		User user= userService.getUser(userId);
		return ResponseEntity.ok(user);
	}
	
	//creating fall back method for circuitbreaker
//	public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
//		log.info("Fallback is executed because service is down: ",ex.getMessage());
//		User.builder()
//		.email("dummy@gmail.com")
//		.name("Dummy")
//		.about("This user is created dummmy because some services is down")
//		.userId("141234")
//		.build();
//		return new ResponseEntity<>(userId,HttpStatus.OK);
//	}
	
	
	//all user get
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		List<User> allUser = userService.getAllUser();
		return ResponseEntity.ok(allUser);
	}
	

}
