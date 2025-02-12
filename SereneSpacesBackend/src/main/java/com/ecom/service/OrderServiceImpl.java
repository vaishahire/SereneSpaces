package com.ecom.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.dao.OrderDao;
import com.ecom.dao.OrderProductDao;
import com.ecom.dao.UserDao;
import com.ecom.dto.OrderProductDto;
import com.ecom.pojo.Order;
import com.ecom.pojo.OrderProduct;
import com.ecom.pojo.User;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderProductDao orderProductDao;

    @Autowired
    private UserDao userDao;

    // Create an Order and add a product to it in one method
    @Override
    public Long createOrderAndAddProduct(OrderProductDto orderProductDto) {
        User user = userDao.findById(orderProductDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create a new Order and set the order date and user
        Order order = new Order();
        order.setUser(user);
        order.setOrderDateTime(LocalDateTime.now());

        // Save the order to generate an orderId
        order = orderDao.save(order);

        // Now that the order is created, add the product to this order
        addOrderProduct(orderProductDto, order.getOrderId());

        return order.getOrderId();
    }

    // Add product to an existing order
    @Override
    public String addOrderProduct(OrderProductDto orderProductDto, Long orderId) {
        // Get the order by orderId
        Order order = orderDao.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Create an OrderProduct with the passed DTO values
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrder(order);
        orderProduct.setProdName(orderProductDto.getProdName());
        orderProduct.setImgUrl(orderProductDto.getImg());
        orderProduct.setDescription(orderProductDto.getDescription());
        orderProduct.setPrice(orderProductDto.getPrice());
        orderProduct.setSelectedQty(orderProductDto.getQuantity());

        // Save the OrderProduct
        orderProductDao.save(orderProduct);

        return "Product added to order successfully.";
    }
    
    @Override
    public List<Order> getOrdersByUser(Long userId) {
        User user = userDao.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch the orders for the user
        List<Order> orders = orderDao.findByUser(user);

        // Lazy loading will automatically load the products when you access order.getOrderProducts()
        return orders;
    }

}
