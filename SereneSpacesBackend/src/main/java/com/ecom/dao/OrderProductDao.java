package com.ecom.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.pojo.Order;
import com.ecom.pojo.OrderProduct;

public interface OrderProductDao extends JpaRepository<OrderProduct, Long>{

	List<OrderProduct> findByOrder(Order order);

}
