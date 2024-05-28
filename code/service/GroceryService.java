package com.grocery_service.service;

import java.util.List;
import java.util.Optional;

import com.grocery_service.entity.Grocery;
import com.grocery_service.entity.reqresentities.RequestItem;
import com.grocery_service.entity.reqresentities.ResponseOrder;
import com.grocery_service.exception.GroceryDoesntFoundException;

public interface GroceryService {
	public List<Grocery> getItemList();
	
	public Optional<Grocery> removeItem(int itemId) throws GroceryDoesntFoundException;

	public Grocery addItem(Grocery item);

	public Optional<Grocery> updateItem(int itemId,Grocery updateItem);

	public ResponseOrder placeOrder(List<RequestItem> itemList,int userId);
}
