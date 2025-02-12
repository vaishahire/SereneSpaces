package com.ecom.pojo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "user_id",nullable =false)
		Long userId;
		
		@Column(length = 30,nullable = false,name = "name")
		String userName;
		
		@Column(length = 70)
		String address;
		
		@Column(name="mobile_number",length = 10)
		String mobileNumber;
		
		@OneToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "authentication" , nullable = false)
		Auth authentication;

		public User(String userName, String address, String mobileNumber, Auth authentication) {
			super();
			this.userName = userName;
			this.address = address;
			this.mobileNumber = mobileNumber;
			this.authentication = authentication;
		}
		
		public User() {
			super();
		}

		public Long getUserId() {
			return userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getMobileNumber() {
			return mobileNumber;
		}

		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

		public Auth getAuthentication() {
			return authentication;
		}

		public void setAuthentication(Auth authentication) {
			this.authentication = authentication;
		}

		@Override
		public String toString() {
			return "User [userId=" + userId + ", userName=" + userName + ", address=" + address + ", mobileNumber="
					+ mobileNumber + ", authentication=" + authentication + "]";
		}
		
		
}

