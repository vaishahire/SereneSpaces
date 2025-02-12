package com.ecom.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.ecom.pojo.Auth;
import io.jsonwebtoken.Claims;


public class CustomUserDetails implements UserDetails {
	private Auth user;
	
	public CustomUserDetails(Auth entity) {
		this.user=entity;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(
				user.getRole().toString()));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}

	public Auth getUser() {
		return user;
	}
	

	

}
