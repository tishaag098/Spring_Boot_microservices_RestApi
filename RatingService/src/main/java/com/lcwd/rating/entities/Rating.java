package com.lcwd.rating.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="user_ratings")
public class Rating {
	@Id
	@Column(name="rating_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer ratingId;
	
	@Column(name="user_id")
	private String userId;
	
	@Column(name="hotel_id")
	private String hotelId;
	
	@Column(name="rating")
	private int rating;
	
	@Column(name="remark")
	private String remark;

}
