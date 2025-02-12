package com.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.UpdateUserProfileDto;
import com.ecom.pojo.User;
import com.ecom.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000") // Enable CORS for this controller
public class UserController {

		@Autowired
		UserService service;
		
		@GetMapping("/getusers")
		public ResponseEntity<?> getAllUser() {
			return ResponseEntity.status(HttpStatus.OK).body(service.getAllUsers());
		}
		
		@PostMapping("/signup")
		public ResponseEntity<?> addUser(@RequestBody User user) {
			
			return ResponseEntity.status(HttpStatus.CREATED).body(service.addUser(user));
		}
		
		
		@PutMapping("/updateuser/{userId}")
	    public ResponseEntity<User> updateUserProfile( @PathVariable Long userId,@RequestBody UpdateUserProfileDto updateUserProfileDTO) {
	        User updatedUser = service.updateUserProfile(userId, updateUserProfileDTO);
	        return ResponseEntity.ok(updatedUser);
	    }
		
		@GetMapping("/getuser/{userId}")
		public ResponseEntity<?> getUserById( @PathVariable Long userId) {
			return ResponseEntity.status(HttpStatus.OK).body(service.getUserById(userId));
		}
		
		@DeleteMapping("/deleteuser/{id}")
		public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		    service.deleteUserById(id);
		    return ResponseEntity.ok("User deleted successfully");
		}
		
}
