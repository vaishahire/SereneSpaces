package com.ecom.pojo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "category_id", nullable = false)
		Long categoryId;
		
		@Column(name = "category_name" , length = 50 , nullable = false)
		String CategoryName;
		

		public Category(String categoryName) {
			super();
			CategoryName = categoryName;
		}
		
		public Category() {
			super();
		}

		public Long getCategoryId() {
			return categoryId;
		}

		public void setCategoryId(Long categoryId) {
			this.categoryId = categoryId;
		}

		public String getCategoryName() {
			return CategoryName;
		}

		public void setCategoryName(String categoryName) {
			CategoryName = categoryName;
		}

		
}
