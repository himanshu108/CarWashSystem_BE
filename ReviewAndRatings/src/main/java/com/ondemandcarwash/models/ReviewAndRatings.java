package com.ondemandcarwash.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ReviewnRatings")
public class ReviewAndRatings {

	@Id
	private int wId;
	private int ratings;
	private String review;

	public ReviewAndRatings() {

	}

	public ReviewAndRatings(int wId, int ratings, String review) {
		super();
		this.wId = wId;
		this.ratings = ratings;
		this.review = review;
	}

	public int getwId() {
		return wId;
	}

	public void setwId(int wId) {
		this.wId = wId;
	}

	public int getRatings() {
		return ratings;
	}

	public void setRatings(int ratings) {
		this.ratings = ratings;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

}
