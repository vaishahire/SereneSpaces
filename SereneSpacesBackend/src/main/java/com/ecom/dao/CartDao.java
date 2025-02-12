package com.ecom.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.pojo.Cart;
import com.ecom.pojo.User;

public interface CartDao extends JpaRepository<Cart, Long> {

	Cart findByUser(User user);

}
