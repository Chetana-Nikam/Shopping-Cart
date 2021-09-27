package com.eshoppingzone.cartservice.entity;

import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Cart {
	
	@Id
	private int cartId;
	
	private double totalPrice;
	
	private String productName;
	
	private double price;
	
	private int quantity;
	
	public Cart() {}
	
	public Cart(int cartId, double totalPrice, String productName, double price, int quantity) {
		
		this.cartId = cartId;
		this.totalPrice = totalPrice;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", totalPrice=" + totalPrice + ", productName=" + productName + ", price="
				+ price + ", quantity=" + quantity + "]";
	}
	
}
