package com.ecom.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ecom.dao.AuthenticationDao;
import com.ecom.pojo.Auth;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	// depcy
	private AuthenticationDao userEntityRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Auth user = userEntityRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Invalid Email !!!"));
		return new CustomUserDetails(user);
	}

}
