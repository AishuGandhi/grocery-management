package com.grocery_service.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grocery_service.entity.UserOrder;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder,Integer>{

}
