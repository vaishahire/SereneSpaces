package com.ecom.pojo;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "order_id",nullable = false)
		Long orderId;
		
		@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true , fetch = FetchType.LAZY)
	    private List<OrderProduct> orderProducts;
		
		@Column(name = "order_date_time",nullable = false)
		LocalDateTime orderDateTime;
		
		@ManyToOne
		@JoinColumn(name="user_id",nullable = false)
		User user;
		
		
		public Order() {
			super();

		}

		

		public Order(List<OrderProduct> orderProducts, LocalDateTime orderDateTime, User user) {
			super();
			this.orderProducts = orderProducts;
			this.orderDateTime = orderDateTime;
			this.user = user;
		}



		public Long getOrderId() {
			return orderId;
		}

		

		public LocalDateTime getOrderDateTime() {
			return orderDateTime;
		}

		public void setOrderDateTime(LocalDateTime orderDateTime) {
			this.orderDateTime = orderDateTime;
		}

		
		
		
		public User getUser() {
			return user;
		}



		public void setUser(User user) {
			this.user = user;
		}



		public List<OrderProduct> getOrderProducts() {
			return orderProducts;
		}

		public void setOrderProducts(List<OrderProduct> orderProducts) {
			this.orderProducts = orderProducts;
		}

		

		
		
		
}
