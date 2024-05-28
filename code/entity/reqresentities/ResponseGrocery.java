package com.grocery_service.entity.reqresentities;

import org.springframework.stereotype.Component;

@Component
public class ResponseGrocery {
	
	private String name;
	
	private int quantity;
	
	private float price;
	
	private float totalPerItem;
	
	private ErrorResponse errRes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getTotalPerItem() {
		return totalPerItem;
	}

	public void setTotalPerItem(float totalPerItem) {
		this.totalPerItem = totalPerItem;
	}

	public ErrorResponse getErrRes() {
		return errRes;
	}

	public void setErrRes(ErrorResponse errRes) {
		this.errRes = errRes;
	}
	
	
}
