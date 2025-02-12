package com.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.dao.ProductDao;
import com.ecom.dao.ReviewDao;
import com.ecom.dao.UserDao;
import com.ecom.dto.ReviewDto;
import com.ecom.pojo.Product;
import com.ecom.pojo.Review;
import com.ecom.pojo.User;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

   
    @Override
    public String addReview(ReviewDto reviewDTO) {
        Product product = productDao.findById(reviewDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + reviewDTO.getProductId()));

        User user = userDao.findById(reviewDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + reviewDTO.getUserId()));

        Review review = new Review();
        review.setCount(reviewDTO.getCount());
        review.setComment(reviewDTO.getComment());
        review.setProducts(product);
        review.setUser(user);

        reviewDao.save(review);
        return "Review added successfully!";
    }

    
    @Override
    public Review updateReview(Long reviewId, ReviewDto reviewDTO) {
        Review existingReview = reviewDao.findById(reviewId).orElse(null);

        if (existingReview != null) {
            existingReview.setCount(reviewDTO.getCount());
            existingReview.setComment(reviewDTO.getComment());

            Product product = productDao.findById(reviewDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + reviewDTO.getProductId()));

            User user = userDao.findById(reviewDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + reviewDTO.getUserId()));

            existingReview.setProducts(product);
            existingReview.setUser(user);

            return reviewDao.save(existingReview);
        } else {
            return null; 
        }
    }

   
    @Override
    public boolean deleteReview(Long reviewId) {
        if (reviewDao.existsById(reviewId)) {
            reviewDao.deleteById(reviewId);
            return true;
        } else {
            return false; 
        }
    }

    
    @Override
    public Review getReviewById(Long reviewId) {
        return reviewDao.findById(reviewId).orElse(null); 
    }


    @Override
	public List<Review> getReviewsByProductId(Long productId) {
    	 Product product = productDao.findById(productId)
                 .orElseThrow(() -> new RuntimeException("Product not found"));
         return reviewDao.findByProducts(product);
	}

}
