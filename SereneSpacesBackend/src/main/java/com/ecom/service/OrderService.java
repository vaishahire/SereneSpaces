package com.ecom.service;

import java.util.List;

import com.ecom.dto.OrderProductDto;
import com.ecom.pojo.Order;

public interface OrderService {

	Long createOrderAndAddProduct(OrderProductDto orderProductDto);

	String addOrderProduct(OrderProductDto orderProductDto, Long orderId);

	List<Order> getOrdersByUser(Long userId);

	
	
}
