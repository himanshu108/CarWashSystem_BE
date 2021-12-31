package com.ondemandcarwash.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ondemandcarwash.models.ReviewAndRatings;

public interface ReviewAndRatingsRepository extends MongoRepository<ReviewAndRatings, Integer> {

}
