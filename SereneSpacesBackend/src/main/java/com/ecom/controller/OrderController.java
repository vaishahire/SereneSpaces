package com.ecom.controller;

import com.ecom.dto.OrderProductDto;
import com.ecom.pojo.Order;
import com.ecom.service.CartService;
import com.ecom.service.OrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;
    
    // Single method to create order and add a product to it
//    @PostMapping("/addOrder")
//    public ResponseEntity<?> createOrderAndAddProduct(@RequestBody OrderProductDto orderProductDto) {
//        try {
//            // First create an order for the user
//            Long orderId = orderService.createOrderAndAddProduct(orderProductDto);
//
//            // After creating the order, add the product to it
////            orderService.addOrderProduct(orderProductDto, orderId);
//            
//            // If the order is created successfully, delete all cart items for the user
//            cartService.deleteCartItemsByUserId(orderProductDto.getUserId()); // Pass userId from the DTO to delete cart items
//
//            return ResponseEntity.ok("Order created and product added successfully.");
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(400).body(e.getMessage());
//        }
//    }
    
    @PostMapping("/addOrder")
    public ResponseEntity<?> createOrderAndAddProduct(@RequestBody List<OrderProductDto> orderProductDtos) {
        try {
        	 Long orderId=null;
            // Process each order item in the list
            for (OrderProductDto orderProductDto : orderProductDtos) {
                // Here you can process each order item individually
               orderId = orderService.createOrderAndAddProduct(orderProductDto);
                cartService.deleteCartItemsByUserId(orderProductDto.getUserId()); 
            }

            
//            return ResponseEntity.ok("Order created and product added successfully.");
            return ResponseEntity.ok(orderId);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    
    @GetMapping("/getOrders/{userId}")
    public ResponseEntity<?> getOrdersByUser(@PathVariable Long userId) {
        try {
            List<Order> orders = orderService.getOrdersByUser(userId);
            if (orders.isEmpty()) {
                return ResponseEntity.status(404).body("No orders found for this user.");
            }
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error. Please try again later.");
        }
    }

}
