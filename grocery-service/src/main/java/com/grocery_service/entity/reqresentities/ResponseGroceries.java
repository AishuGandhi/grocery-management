package com.grocery_service.entity.reqresentities;

import org.springframework.stereotype.Component;

import com.grocery_service.entity.Grocery;

@Component
public class ResponseGroceries {

	private Grocery grocery;

	private ErrorResponse errRes;

	public Grocery getGrocery() {
		return grocery;
	}

	public void setGrocery(Grocery grocery) {
		this.grocery = grocery;
	}

	public ErrorResponse getErrRes() {
		return errRes;
	}

	public void setErrRes(ErrorResponse errRes) {
		this.errRes = errRes;
	}

}
