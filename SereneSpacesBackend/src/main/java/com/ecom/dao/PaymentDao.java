package com.ecom.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.pojo.Payment;

public interface PaymentDao extends JpaRepository<Payment, Long>{

}
