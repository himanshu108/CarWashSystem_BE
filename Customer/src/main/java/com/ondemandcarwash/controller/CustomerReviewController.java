package com.ondemandcarwash.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ondemandcarwash.model.ReviewAndRatings;

@RestController
@RequestMapping("/customer")
public class CustomerReviewController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	
	/*
	 * * Below code is for the Customer for the Review * Customer can AddReview and
	 * Delete Review
	 */

	// For Adding Review

	@PostMapping("/addreview") 
	public String addreview (@RequestBody ReviewAndRatings reviewAndRatings) 
	{
	  return restTemplate.postForObject("http://reviewnratings-service/reviewratings/addreview", reviewAndRatings , String.class);
	  
	  }
	

	//Reading All Review 
		@GetMapping("/getallreview") 
		public String getallReview() 
		{
		   return restTemplate.getForObject("http://reviewnratings-service/reviewratings/allreview", String.class);
		  
		  }
		

	// for updating review for washer

		@PutMapping("/updatereview/{id}")
		public String updatereview(@PathVariable("id") int id, @RequestBody ReviewAndRatings reviewAndRatings) {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<ReviewAndRatings> entity = new HttpEntity<ReviewAndRatings>(reviewAndRatings, headers);

			return restTemplate.exchange("http://reviewnratings-service/reviewratings/updatereview/" + id, HttpMethod.PUT, entity, String.class)
					.getBody();
		}

	// for Deleting review

	@DeleteMapping("/deletereview/{id}")
	public String deletereview(@PathVariable("id") int id) 
	{
		restTemplate.delete("http://reviewnratings-service/reviewratings/deletereview/" +id , String.class);
		return " Review is deleted successfully for " + id;
	}


}
