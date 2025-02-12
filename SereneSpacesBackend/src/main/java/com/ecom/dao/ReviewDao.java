package com.ecom.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.pojo.Product;
import com.ecom.pojo.Review;

public interface ReviewDao extends JpaRepository<Review, Long> {

	List<Review> findByProducts(Product product);

}
