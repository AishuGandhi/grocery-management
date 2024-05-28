package com.grocery_service.exception;

public class GroceryDoesntFoundException extends Exception {

	
	private static final long serialVersionUID = -4897029180334950293L;

	public GroceryDoesntFoundException(String msg) {
		super(msg);
	}
}
