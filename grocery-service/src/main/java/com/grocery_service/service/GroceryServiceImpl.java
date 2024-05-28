package com.grocery_service.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery_service.controller.GroceryController;
import com.grocery_service.entity.EmbeddedOrderKey;
import com.grocery_service.entity.Grocery;
import com.grocery_service.entity.Order;
import com.grocery_service.entity.UserOrder;
import com.grocery_service.entity.reqresentities.RequestItem;
import com.grocery_service.entity.reqresentities.ResponseGrocery;
import com.grocery_service.entity.reqresentities.ResponseOrder;
import com.grocery_service.exception.GroceryDoesntFoundException;
import com.grocery_service.repository.GroceryRepository;
import com.grocery_service.repository.OrderRepository;
import com.grocery_service.repository.UserOrderRepository;

@Service
public class GroceryServiceImpl implements GroceryService {
	Logger LOGGER = LoggerFactory.getLogger(GroceryController.class);

	@Autowired
	private GroceryRepository adminRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserOrderRepository userOrderRepository;

	@Override
	public List<Grocery> getItemList() {
		List<Grocery> itemList = null;
		LOGGER.info("Inside getItemList from GroceryService");
		itemList = adminRepository.findAll();
		LOGGER.info("Exiting getItemList from GroceryService");
		return itemList;
	}

	@Override
	public Optional<Grocery> removeItem(int itemId) throws GroceryDoesntFoundException {
		Optional<Grocery> item = null;
		LOGGER.info("Inside removeItem from GroceryService");
		item = adminRepository.findById(itemId);
		if (item.isPresent())
			adminRepository.delete(item.get());
		else
			 throw new GroceryDoesntFoundException("Item Does Not Exist");
		LOGGER.info("Exiting removeItem from GroceryService");
		return item;
	}

	@Override
	public Grocery addItem(Grocery addItem) {
		Grocery item = null;
		LOGGER.info("Inside addItem from GroceryService");
		item = adminRepository.save(addItem);// .orElseThrow(() -> {new ItemDoesntFoundException("Item Does Not
												// Exist");});
		LOGGER.info("Exiting addItem from GroceryService");

		return item;
	}

	@Override
	public Optional<Grocery> updateItem(int itemId, Grocery updateItem) {

		LOGGER.info("Inside updateItem from GroceryService");

		Optional<Grocery> item = adminRepository.findById(itemId);
		if (item.isPresent()) {
			item.get().setName(updateItem.getName());
			item.get().setPrice(updateItem.getPrice());
			item.get().setQuantity(updateItem.getQuantity());
			adminRepository.save(item.get());
		}
		LOGGER.info("Exiting updateItem from GroceryService");
		return item;
	}

	@Override
	public ResponseOrder placeOrder(List<RequestItem> itemList, int userId) {

		LOGGER.info("Inside placeOrder from GroceryService");
		List<ResponseGrocery> list = new ArrayList<>();
		Random random = new Random();
		ResponseOrder resOrder;
		ResponseGrocery resItem;
		int uniqueOrderIdPerOrder = Math.abs(random.nextInt());
		float billTotal = 0.0f;

		LOGGER.info("UniqueOrderId" + uniqueOrderIdPerOrder);
		for (int i = 0; i < itemList.size(); i++) {
			Optional<Grocery> item = findItemById(itemList.get(i).getItemId());
			if (item.isPresent()) {
				LOGGER.info("Item Found");
				Grocery updateItem = item.get();
				RequestItem requestedItem = itemList.get(i);

				LOGGER.info("Database Item:" + updateItem);
				LOGGER.info("requestedItem Item:" + requestedItem);
				
				// update quantity in grocery table for items
			    updateGroceryQuantity(requestedItem.getQuantity(), updateItem );

				// add entry of above date in order table
			    saveOrder(uniqueOrderIdPerOrder,updateItem.getId(),requestedItem.getQuantity());
				
			
				// for sending in response
				resItem = createResponseEntityList(updateItem,requestedItem);
				list.add(resItem); 
				
				// Bill total
				billTotal += (requestedItem.getQuantity() * updateItem.getPrice());
			}
		}

		// add user id needs and order id in user order table
		createUserOrderEntity(uniqueOrderIdPerOrder,userId);

		// response your total bill amount and grocery name, quantity,price per unit and item total
		resOrder = createResponseEntity(list,billTotal);
		return resOrder;
	}

	private Optional<Grocery> findItemById(int itemId){
		return adminRepository.findById(itemId);
	}
	
	private void updateGroceryQuantity(int requestedQuantity, Grocery item) {
		LOGGER.info("Inside updateGroceryQuantity");
		int q = item.getQuantity() - requestedQuantity;
		item.setQuantity(q);
		adminRepository.save(item);
		LOGGER.info("Exiting updateGroceryQuantity");
	}
	
	private void saveOrder( int orderId,int itemId,int requestedQuantity) {
		LOGGER.info("Inside saveOrder");
		Order order = new Order();
		EmbeddedOrderKey key = new EmbeddedOrderKey();
		key.setItemId(itemId);
		key.setOrderId(orderId);
		order.setOrderId(key);
		order.setQuantity(requestedQuantity);
		orderRepository.save(order);
		LOGGER.info("Exiting saveOrder");
	}
	private ResponseGrocery createResponseEntityList(Grocery updateItem, RequestItem requestedItem) {
		LOGGER.info("Inside createResponseEntityList");
		ResponseGrocery resItem = new ResponseGrocery();
		resItem.setName(updateItem.getName());
		resItem.setPrice(updateItem.getPrice());
		resItem.setQuantity(requestedItem.getQuantity());
		resItem.setTotalPerItem(requestedItem.getQuantity() * updateItem.getPrice());
		LOGGER.info("Exiting createResponseEntityList"+resItem);
		return resItem;
	}
	
	private ResponseOrder createResponseEntity(List<ResponseGrocery> list, float total) {
		LOGGER.info("Inside createResponseEntity");
		ResponseOrder resOrder = new ResponseOrder();
		resOrder.setGroceryList(list);
		resOrder.setTotal(total);
		LOGGER.info("Exiting createResponseEntity");
		return resOrder;
	}
	
	private void createUserOrderEntity(int orderId,int userId) {
		LOGGER.info("Inside createUserOrderEntity");
		UserOrder userOrder = new UserOrder();
		userOrder.setOrderId(orderId);
		userOrder.setUserId(userId);
		userOrder.setLocalDateTime(LocalDateTime.now());
		userOrderRepository.save(userOrder);
		LOGGER.info("Exiting createUserOrderEntity");
	}
}
