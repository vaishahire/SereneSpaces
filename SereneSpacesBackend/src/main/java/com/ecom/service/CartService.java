package com.ecom.service;

import java.util.List;

import com.ecom.dto.CartProductDetailsDto;
import com.ecom.pojo.Cart;
import com.ecom.pojo.CartProduct;

public interface CartService {

	Cart getOrCreateCart(Long userId);
	String addProductToCart(Long userId, Long productId, Integer quantity);
	String removeProductFromCart(Long userId, Long productId);
	List<CartProductDetailsDto> getCartProducts(Long userId);
	void deleteCartItemsByUserId(Long userId);
	
}
