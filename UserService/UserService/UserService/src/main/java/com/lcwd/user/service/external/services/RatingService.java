package com.lcwd.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lcwd.user.service.entities.Rating;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {
	
	//GET
	
	//POST
	
	@PostMapping("/ratings")
	public ResponseEntity<Rating> create(@RequestBody Rating rating);
	
	
//	//PUT
//	@PutMapping("/ratings/{ratingId}")
//	public Rating updateRating(@PathVariable String ratingId,Rating rating);
//	
//	//DELETE
//	@DeleteMapping("/ratings/{ratingId}")
//	public void delteRating(@PathVariable String ratingId);
//	

}
