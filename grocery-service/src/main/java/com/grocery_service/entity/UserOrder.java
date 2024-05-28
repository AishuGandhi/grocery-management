package com.grocery_service.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "userorder")
public class UserOrder {

	@Id
	@Column(name = "order_id")
	private int orderId;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "dt_Time")
	private LocalDateTime localDateTime;

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
}
