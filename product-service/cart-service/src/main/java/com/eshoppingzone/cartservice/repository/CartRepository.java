package com.eshoppingzone.cartservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eshoppingzone.cartservice.entity.Cart;


public interface CartRepository extends MongoRepository<Cart, Integer> {
	
	

}
