package com.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dao.UserDao;
import com.ecom.dto.SigninRequest;
import com.ecom.dto.SigninResponse;
import com.ecom.security.JwtUtils;
import com.ecom.pojo.User;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/authusers")
@CrossOrigin(origins = "http://localhost:3000") 
public class AuthController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authMgr;
    
    @Autowired
    private UserDao userDao;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody @Valid SigninRequest request) {
        System.out.println("Attempting to sign in: " + request.getEmail());

        try {
            // 1. Create authentication token with email and password
            Authentication authentication = authMgr.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            
            // 2. Fetch user details to get userId
            User user = userDao.findByAuthentication_Email(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // 3. Generate JWT token with userId
            String jwtToken = jwtUtils.generateJwtToken(authentication, user.getUserId());
            
            System.out.println("Authentication successful for: " + request.getEmail());

            return ResponseEntity.ok(new SigninResponse(jwtToken, "Successful Authentication!", user.getUserId()));

        } catch (AuthenticationException e) {
            System.out.println("Authentication failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}
