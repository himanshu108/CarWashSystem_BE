package com.ondemandcarwash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/washer")
public class WasherReviewController {

	@Autowired
	private RestTemplate restTemplate;

	// Reading All Review
	@GetMapping("/getallreview")
	public String getallReview() {
		return restTemplate.getForObject("http://reviewnratings-service/reviewratings/allreview", String.class);

	}

	// Reading All Review by id
	@GetMapping("/getreview/{id}")
	public String getReviewByid(@PathVariable int id) {
		return restTemplate.getForObject("http://reviewnratings-service/reviewratings/review/" + id, String.class);

	}

}
