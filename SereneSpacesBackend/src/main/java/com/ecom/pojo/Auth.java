package com.ecom.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "authentications")
public class Auth {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "authentication_id",nullable =false)
	Long id;
	

	@Column(length = 50,nullable =false)
	String email;
	
	@Column(length = 300,nullable =false)
	String password;
	
	@Enumerated(EnumType.STRING)
	Role role; 
	
	
	public Auth(String email, String password, Role role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}
	public Auth() {
		super();
	
	}
	public Long getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Authentication [id=" + id + ", email=" + email + ", password=" + password + ", role=" + role + "]";
	}
	
	
}
