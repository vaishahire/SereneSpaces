package com.ecom.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.pojo.Auth;

public interface AuthenticationDao extends JpaRepository<Auth, Long>{
	
	Optional<Auth> findByEmail(String email);

}
