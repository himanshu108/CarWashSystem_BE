package com.ondemandcarwash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ondemandcarwash.models.ReviewAndRatings;
import com.ondemandcarwash.repository.ReviewAndRatingsRepository;

@Service
public class ReviewAndRatingsService {
	
	@Autowired
	private ReviewAndRatingsRepository reviewAndRatingsRepository;

	public ReviewAndRatings addReview(ReviewAndRatings reviewAndRatings) {
		return reviewAndRatingsRepository.save(reviewAndRatings);
		
		
		
	}

	public List<ReviewAndRatings> getallreview() {
		List<ReviewAndRatings> ratings = reviewAndRatingsRepository.findAll();
		System.out.println("Getting reviews from DB" + ratings);
		return ratings;
	}

	public void deletereviewById(int id) {
		reviewAndRatingsRepository.deleteById(id);
		
	}

}
