package com.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.dao.UserDao;
import com.ecom.dto.UpdateUserProfileDto;
import com.ecom.pojo.User;



@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao dao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<User> getAllUsers() {
		return dao.findAll();
	}

	@Override
	public String addUser(User user) {
		try {
			user.getAuthentication().setPassword(passwordEncoder.encode(
					user.getAuthentication().getPassword()));
			dao.save(user);
			return "User Added Successfully";
		}catch (Exception e) {
			return "Something went wrong "+e;
		}
		
	}

	
	
	 public User updateUserProfile(Long userId, UpdateUserProfileDto updateUserProfileDTO) {
	        User user = dao.findById(userId)
	                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

	        user.setUserName(updateUserProfileDTO.getUserName());
	        user.setAddress(updateUserProfileDTO.getAddress());
	        user.setMobileNumber(updateUserProfileDTO.getMobileNumber());

	        return dao.save(user);
	    }

	@Override
	public User getUserById(Long userId) {
		 User user = dao.findById(userId)
	                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
		return user;

	}

	@Override
	public void deleteUserById(Long id) {
	    if (dao.existsById(id)) { // Check if user exists
	        dao.deleteById(id);
	    } else {
	        throw new RuntimeException("User not found with ID: " + id);
	    }
	}
	
}
