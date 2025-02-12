package com.ecom.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecom.dto.PaymentDto;
import com.ecom.service.PaymentService;

@RestController
@RequestMapping("/payments")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {
 
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/addpayment")
    public ResponseEntity<?> makePayment(@RequestBody PaymentDto paymentDTO) {
        String response = paymentService.processPayment(paymentDTO);
        return ResponseEntity.ok(response);
    }
}
