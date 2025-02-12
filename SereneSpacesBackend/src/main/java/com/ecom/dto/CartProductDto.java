package com.ecom.dto;

import java.util.List;

public class CartProductDto {

	Long userId;
	Long productId;	
	Integer quantity;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public CartProductDto(Long userId, Long productId, Integer quantity) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
	}
	
	
}
