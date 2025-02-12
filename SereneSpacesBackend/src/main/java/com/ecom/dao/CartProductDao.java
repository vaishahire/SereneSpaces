package com.ecom.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.pojo.Cart;
import com.ecom.pojo.CartProduct;
import com.ecom.pojo.Product;

public interface CartProductDao extends JpaRepository<CartProduct, Long>{
	CartProduct findByCartAndProduct(Cart cart, Product product);
}
