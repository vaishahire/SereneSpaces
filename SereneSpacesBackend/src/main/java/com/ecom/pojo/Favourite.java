package com.ecom.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "favourites")
public class Favourite {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "favourite_id", nullable = false)
		Long favId;
		
		@ManyToOne
		@JoinColumn(name = "product_id",nullable = false)
		Product products;
		
		@ManyToOne
		@JoinColumn(name = "user_id",nullable = false)
		User user;

		public Favourite(Product products, User user) {
			super();
			this.products = products;
			this.user = user;
		}
		
		public Favourite() {
			super();
		}

		public Long getFavId() {
			return favId;
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
			return "Favourite [favId=" + favId + ", products=" + products + ", user=" + user + "]";
		}
		
		
} 
