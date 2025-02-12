package com.ecom.controller;
import com.ecom.dto.ReviewDto;
import com.ecom.pojo.Review;
import com.ecom.service.ReviewServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {

    @Autowired
    private ReviewServiceImpl reviewService;

   
    @GetMapping("/getbyid/{reviewId}")
    public ResponseEntity<?> getReviewById(@PathVariable Long reviewId) {
        Review review = reviewService.getReviewById(reviewId);
        if (review != null) {
            return ResponseEntity.status(HttpStatus.OK).body(review);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found with id: " + reviewId);
        }
    }

    
    @PostMapping("/addreview")
    public ResponseEntity<?> addReview(@RequestBody ReviewDto reviewDTO) {
        String responseMessage = reviewService.addReview(reviewDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    
    @PutMapping("/updatereview/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable Long reviewId, @RequestBody ReviewDto reviewDTO) {
        Review updatedReview = reviewService.updateReview(reviewId, reviewDTO);
        if (updatedReview != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedReview);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found with id: " + reviewId);
        }
    }

    
    @DeleteMapping("/deletereview/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
        boolean isDeleted = reviewService.deleteReview(reviewId);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found with id: " + reviewId);
        }
    }
    
    @GetMapping("/getreviewbyproduct/{productId}")
    public ResponseEntity<?> getReviewsByProductId(@PathVariable Long productId) {
        List<Review> reviews = reviewService.getReviewsByProductId(productId);
        return ResponseEntity.ok(reviews);
    }
}
