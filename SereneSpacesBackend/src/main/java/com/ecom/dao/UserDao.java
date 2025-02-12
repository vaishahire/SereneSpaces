package com.ecom.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.pojo.User;

public interface UserDao extends JpaRepository<User,Long>{
	Optional<User> findByAuthentication_Email(String email);

//	User findByEmail(String email);
}
