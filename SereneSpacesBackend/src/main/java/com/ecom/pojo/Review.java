package com.ecom.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviews")
public class Review {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="review_id",nullable = false)
		Long reviewId;
		
		Integer count;
		
		@Column(length =50 )
		String comment;
		
		@ManyToOne
		@JoinColumn(nullable = false)
		Product products;
		
		@OneToOne
		@JoinColumn(nullable = false)
		User user;

		public Review(Integer count, String comment, Product products, User user) {
			super();
			this.count = count;
			this.comment = comment;
			this.products = products;
			this.user = user;
		}
		
		public Review() {
			super();

		}

		public Long getReviewId() {
			return reviewId;
		}

		public Integer getCount() {
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public Product getProducts() {
			return products;
		}

		public void setProducts(Product products) {
			this.products = products;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		@Override
		public String toString() {
			return "Review [reviewId=" + reviewId + ", count=" + count + ", comment=" + comment + ", products="
					+ products + ", user=" + user + "]";
		}
		
		
}
