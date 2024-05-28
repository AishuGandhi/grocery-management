package com.grocery_service.entity.reqresentities;

import org.springframework.stereotype.Component;

@Component
public class RequestItem {
	
	private int quantity;
	
	private int itemId;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	
}
