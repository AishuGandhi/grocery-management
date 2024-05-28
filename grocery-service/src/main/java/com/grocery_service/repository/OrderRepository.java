package com.grocery_service.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grocery_service.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>{

	/*
	 * @Query("insert into Order (order_id,quantity_per_item,item_id) select :orderId,:quantity,:itemId from Dual"
	 * ) public Order insert(Order order);
	 */
}
