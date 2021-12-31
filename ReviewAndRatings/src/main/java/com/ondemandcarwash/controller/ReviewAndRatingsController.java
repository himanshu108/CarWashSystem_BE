package com.ondemandcarwash.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ondemandcarwash.exception.ApiRequestException;
import com.ondemandcarwash.models.ReviewAndRatings;
import com.ondemandcarwash.repository.ReviewAndRatingsRepository;
import com.ondemandcarwash.service.ReviewAndRatingsService;


@RestController
@RequestMapping("/reviewratings")
public class ReviewAndRatingsController {

	@Autowired
	private ReviewAndRatingsService reviewAndRatingsService;

	@Autowired
	private ReviewAndRatingsRepository reviewAndRatingsRepository;

	// Creating/Adding Review
	@PostMapping("/addreview")
	public String saveReview(@RequestBody ReviewAndRatings reviewAndRatings) {
		reviewAndRatingsService.addReview(reviewAndRatings);
		return "Thanks for your Review " + reviewAndRatings;
	}

	// Reading all Review
	@GetMapping("/allreview")
	public List<ReviewAndRatings> getallreview() {
		return reviewAndRatingsService.getallreview();
	}

	// Reading Review by wId
	@GetMapping("/review/{id}")
	public Optional<ReviewAndRatings> getReviewById(@PathVariable int id) throws ApiRequestException {
		return Optional.of(reviewAndRatingsRepository.findById(id)
				.orElseThrow(() -> new ApiRequestException("REVIEW NOT FOUND WITH THIS ID ::")));
	}

	// Updating Review Data by Id
	@PutMapping("/updatereview/{id}")
	public ResponseEntity<Object> updateReview(@PathVariable int id, @RequestBody ReviewAndRatings reviewAndRatings) {
		boolean isReviewExist = reviewAndRatingsRepository.existsById(id);
		if (isReviewExist) {
			reviewAndRatingsRepository.save(reviewAndRatings);
			return new ResponseEntity<Object>("Review updated successfully for id " + id, HttpStatus.OK);
		} else {
			throw new ApiRequestException("CAN NOT UPDATE AS REVIEW NOT FOUND WITH THIS ID ::");
		}

	}

	// Deleting Review by wId
	@DeleteMapping("/deletereview/{id}")
	public ResponseEntity<Object> deleteReview(@PathVariable int id) {
		boolean isReviewExist = reviewAndRatingsRepository.existsById(id);
		if (isReviewExist) {
			reviewAndRatingsService.deletereviewById(id);
			return new ResponseEntity<Object>("Review is deleted with id" + id, HttpStatus.OK);
		} else {
			throw new ApiRequestException("CAN NOT DELETE REVIEW ,AS REVIEW NOT FOUND WITH THIS ID ::");
		}

	}

}
