package com.grocery_service.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grocery_service.entity.Grocery;
import com.grocery_service.entity.reqresentities.ErrorResponse;
import com.grocery_service.entity.reqresentities.RequestItem;
import com.grocery_service.entity.reqresentities.ResponseGroceries;
import com.grocery_service.entity.reqresentities.ResponseOrder;
import com.grocery_service.exception.GroceryDoesntFoundException;
import com.grocery_service.service.GroceryService;

@RestController
@RequestMapping("/groceryshop")
public class GroceryController {

	Logger LOGGER = LoggerFactory.getLogger(GroceryController.class);

	@Autowired
	private GroceryService adminService;

	@GetMapping("/getAll")
	public List<Grocery> getItemList() {
		LOGGER.info("Inside getItemList from GroceryController");
		List<Grocery> itemList = null;
		itemList = adminService.getItemList();
		LOGGER.info("Inside getItemList from GroceryController");
		return itemList;
	}

	@DeleteMapping("/removeItem")
	public ResponseGroceries removeItem(@RequestParam(name = "itemId", required = true) int itemId) {
		LOGGER.info("Inside removeItem from GroceryController");
		ResponseGroceries res = new ResponseGroceries();
		Optional<Grocery> item = null;
		try {
		item = adminService.removeItem(itemId);
		res.setGrocery(item.get());
		}
		catch(GroceryDoesntFoundException exception) {
			ErrorResponse errRes = new ErrorResponse();
			errRes.setErrCode(001);
			errRes.setErrMsg(exception.getMessage());
			res.setErrRes(errRes);
		}
		LOGGER.info("Inside removeItem from GroceryController");
		return res;
	}

	@PostMapping("/addItem")
	public Grocery addItem(@RequestBody Grocery addItem) {
		LOGGER.info("Inside addItem from GroceryController");
		Grocery item = null;
		item = adminService.addItem(addItem);
		LOGGER.info("Inside addItem from GroceryController");
		return item;
	}

	@PutMapping("/updateItem")
	public Optional<Grocery> updateItem(@RequestParam(name = "itemId", required = true) int itemId,
			@RequestBody Grocery updateItem) {
		LOGGER.info("Inside addItem from GroceryController");

		Optional<Grocery> item = adminService.updateItem(itemId, updateItem);
		LOGGER.info("Inside addItem from GroceryController");
		return item;
	}
	
	@PostMapping("/placeOrder")
	public ResponseOrder placeOrder(@RequestParam(name = "userId", required = true) int userId,@RequestBody List<RequestItem> itemList) {
		LOGGER.info("Inside placeOrder from GroceryController");
		LOGGER.info("Exiting placeOrder from GroceryController");
		return adminService.placeOrder(itemList,userId);
	}
}
