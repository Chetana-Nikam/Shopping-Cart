package com.eshoppingzone.productservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import com.eshoppingzone.productservice.entity.Product;
import com.eshoppingzone.productservice.repository.ProductRepository;

@SpringBootTest
class ProductServiceApplicationTests {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Test
	@Order(1)
	public void addProduct() {
		
		Product product = new Product(); 
			product.setProductId(1);
			product.setProductType("Accessories");
			product.setProductName("Fastrack Watch");
			product.setCategory("Electronics");
			product.setImage("link");
			product.setPrice(2000);
			product.setDescription("Women fastrack watch");
			productRepository.save(product);
			assertNotNull(productRepository.findById(1).get());
		}
		
		@Test
		@Order(2)
		public void getAllProduct() {
			Product product = productRepository.findById(1).get();
			assertEquals("Electronics", product.getProductType());
		}
		
		@Test
		@Order(3)
		public void updateProduct() {
			Product product = productRepository.findById(1).get();
			product.setCategory("Electronics");
			productRepository.save(product);
			assertNotEquals("Electronics", productRepository.findById(1).get().getCategory());
		}
		
	}

//	@Test
//	void contextLoads() {
//	}


