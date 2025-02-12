package com.ecom.service;

import java.util.List;

import com.ecom.dto.UpdateUserProfileDto;
import com.ecom.pojo.User;

public interface UserService {
	List<User> getAllUsers();
	String addUser(User user);
	User updateUserProfile(Long userId, UpdateUserProfileDto updateUserProfileDTO);
	User getUserById(Long userId);
	void deleteUserById(Long id);
}
