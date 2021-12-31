package com.ondemandcarwash.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/admin")
public class AdminWasherController {

	@Autowired
	private RestTemplate restTemplate;

	/*
	 * * Below code is for the Admin for to view Washer *
	 */

	// Reading All Review
	@GetMapping("/getallwasher")
	public String getallWasher() {
		return restTemplate.getForObject("http://localhost:8003/washer/allwashers", String.class);

	}

	// Reading Washer ById
	@GetMapping("/getwasher/{id}")
	public String getWasherById(@PathVariable int id) {
		return restTemplate.getForObject("http://localhost:8003/washer/allwashers/" + id, String.class);

	}

}
