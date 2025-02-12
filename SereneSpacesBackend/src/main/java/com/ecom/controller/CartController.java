package com.ecom.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.CartProductDetailsDto;
import com.ecom.dto.CartProductDto;
import com.ecom.service.CartService;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/addCart")
    public ResponseEntity<?> addProductToCart(@RequestBody CartProductDto cartProductDto) {
        String message = cartService.addProductToCart(cartProductDto.getUserId(), cartProductDto.getProductId(), cartProductDto.getQuantity());
        
        // Determine the response status based on the message
        if (message.startsWith("Requested quantity exceeds")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        }
    }

    @DeleteMapping("/removeCart/{userId}/{productId}")
    public ResponseEntity<?> removeProductFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        String message = cartService.removeProductFromCart(userId, productId);
        
        // Assuming that if the product is not found, you want to return a NOT_FOUND status
        if (message.equals("Product not found in the cart.")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }

    @GetMapping("/Cartproducts/{userId}")
    public ResponseEntity<?> getCartProducts(@PathVariable Long userId) {
        List<CartProductDetailsDto> cartProducts = cartService.getCartProducts(userId);
     // If cartProducts is null or empty, return an empty list
        if (cartProducts == null) {
            cartProducts = Collections.emptyList();
        }
        return ResponseEntity.status(HttpStatus.OK).body(cartProducts);
    }
}