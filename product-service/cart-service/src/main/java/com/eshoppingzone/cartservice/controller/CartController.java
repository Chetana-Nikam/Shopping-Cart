package com.eshoppingzone.cartservice.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.eshoppingzone.cartservice.entity.Cart;
import com.eshoppingzone.cartservice.repository.CartRepository;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartRepository cartRepository;
	
	@GetMapping("/getallcart")
	public List<Cart> getAllCart(){
		return cartRepository.findAll();
	}
	
	@PostMapping("/addcart")
	public ResponseEntity<Cart> addCart(@RequestBody Cart cart){
		try {
			cartRepository.save(cart);
			return new ResponseEntity<>(cart, HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/cartId/{cartId")
	public ResponseEntity<Cart> getCartById(@PathVariable("cartId") int id){
		Optional<Cart> cartdata = cartRepository.findById(id);
		if(cartdata.isPresent()) {
			return new ResponseEntity<>(cartdata.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping("/cartId/{cartId}")
	public ResponseEntity<Cart> updateCart(@PathVariable("cartId") int id, @RequestBody Cart cart){
		Optional<Cart> productdata = cartRepository.findById(id);
		if(productdata.isPresent()) {
			Cart prod = productdata.get();
			prod.setTotalPrice(cart.getTotalPrice());
			prod.setProductName(cart.getProductName());
			prod.setPrice(cart.getPrice());
			prod.setQuantity(cart.getQuantity());
			return new ResponseEntity<>(cartRepository.save(prod), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
