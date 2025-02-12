package com.ecom.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.pojo.Order;
import com.ecom.pojo.User;

public interface OrderDao extends JpaRepository<Order, Long> {

	List<Order> findByUser(User user);

}
