package com.grocery_service.entity.reqresentities;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ResponseOrder {

	private List<ResponseGrocery> groceryList;
	
	private float total;

	public List<ResponseGrocery> getGroceryList() {
		return groceryList;
	}

	public void setGroceryList(List<ResponseGrocery> groceryList) {
		this.groceryList = groceryList;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
}
