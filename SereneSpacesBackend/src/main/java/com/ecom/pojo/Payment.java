package com.ecom.pojo;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "payment_id" , nullable = false)
		Long payId;
		
		@Column(name = "payment_type",nullable = false)
		String payType;
		
		@Column(name="amount", nullable = false)
		double payAmt;
		
		@Column(name = "payment_date_time")
		LocalDateTime payDateTime;

		@OneToOne		
		@JoinColumn(name="order_id" ,nullable = false)
		Order order;

		@ManyToOne		
		@JoinColumn(name="user_id" ,nullable = false)
		User user;
		
		
		
		public Payment(String payType, double payAmt, LocalDateTime payDateTime, Order order, User user) {
			super();
			this.payType = payType;
			this.payAmt = payAmt;
			this.payDateTime = payDateTime;
			this.order = order;
			this.user = user;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Payment() {
			super();
		}

		public Long getPayId() {
			return payId;
		}

		
		public String getPayType() {
			return payType;
		}

		public void setPayType(String payType) {
			this.payType = payType;
		}

		public double getPayAmt() {
			return payAmt;
		}

		public void setPayAmt(double payAmt) {
			this.payAmt = payAmt;
		}

		public LocalDateTime getPayDateTime() {
			return payDateTime;
		}

		public void setPayDateTime(LocalDateTime payDateTime) {
			this.payDateTime = payDateTime;
		}

		public Order getOrder() {
			return order;
		}

		public void setOrder(Order order) {
			this.order = order;
		}

		@Override
		public String toString() {
			return "Payment [payId=" + payId + ", payType=" + payType + ", payAmt=" + payAmt + ", payDateTime="
					+ payDateTime + ", order=" + order + "]";
		}
		
		
}
