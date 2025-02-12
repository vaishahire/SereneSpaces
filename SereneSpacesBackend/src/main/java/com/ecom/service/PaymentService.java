package com.ecom.service;

import com.ecom.dto.PaymentDto;


public interface PaymentService {
	String processPayment(PaymentDto paymentDto);
}
