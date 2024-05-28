package com.grocery_service.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grocery_service.entity.Grocery;

@Repository
public interface GroceryRepository extends JpaRepository<Grocery,Integer>{

}
