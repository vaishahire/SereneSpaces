package com.ecom.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.dao.OrderDao;
import com.ecom.dao.PaymentDao;
import com.ecom.dao.UserDao;
import com.ecom.dto.PaymentDto;
import com.ecom.pojo.Order;
import com.ecom.pojo.Payment;
import com.ecom.pojo.User;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Autowired
    private OrderDao orderDao;
    
    @Autowired
    private UserDao userDao;

    @Override
    public String processPayment(PaymentDto paymentDto) {
        Order order = orderDao.findById(paymentDto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        User user = userDao.findById(paymentDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Payment payment = new Payment();
        payment.setPayType(paymentDto.getPayType());
        payment.setPayAmt(paymentDto.getPayAmt());
        payment.setPayDateTime(LocalDateTime.now());
        payment.setOrder(order);
        payment.setUser(user);

        paymentDao.save(payment);

        return "Payment Successful";
    }
}
