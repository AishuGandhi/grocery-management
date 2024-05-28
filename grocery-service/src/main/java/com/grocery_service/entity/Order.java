package com.grocery_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "orderdetails")
public class Order {

	@EmbeddedId
	private EmbeddedOrderKey orderId;

	
	@Column(name = "quantity_per_item")
	private int quantity;

	

	public EmbeddedOrderKey getOrderId() {
		return orderId;
	}

	public void setOrderId(EmbeddedOrderKey orderId) {
		this.orderId = orderId;
	}

	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
