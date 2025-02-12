package com.ecom.service;

import java.util.List;

import com.ecom.dto.ReviewDto;
import com.ecom.pojo.Review;

public interface ReviewService {

	String addReview(ReviewDto reviewDTO);

	Review updateReview(Long reviewId, ReviewDto reviewDTO);

	boolean deleteReview(Long reviewId);

	Review getReviewById(Long reviewId);

	public List<Review> getReviewsByProductId(Long productId);
}
