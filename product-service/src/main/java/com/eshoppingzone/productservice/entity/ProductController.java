package com.eshoppingzone.productservice.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshoppingzone.productservice.repository.IdRepository;
import com.eshoppingzone.productservice.repository.ProductRepository;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	IdRepository idRepository;
	
	@CrossOrigin(origins= "http://localhost:3000")
	@PostMapping("/product")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		try {
			product.setProductId(nextId());
			productRepository.save(product);
				return new ResponseEntity<>(product, HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	

	@GetMapping("/getallproducts")
	public List<Product> getAllProduct(){
		return productRepository.findAll();
	}
	
	@CrossOrigin(origins= "http://localhost:3000")
	@GetMapping("/productId/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable("productId") int id){
		Optional<Product> productOptional = productRepository.findById(id);
		if(productOptional.isPresent()) {
			return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/productName/{productName}")
	public ResponseEntity<Product> getProductByName(@PathVariable("productName") String name){
		Optional<Product> productdata = productRepository.findByProductName(name);
		if(productdata.isPresent()) {
			return new ResponseEntity<>(productdata.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@CrossOrigin(origins= "http://localhost:3000")

	@PutMapping("/productId/{productId}")
	public ResponseEntity<Product> updateProduct(@PathVariable("productId") int id, @RequestBody Product product){
		Optional<Product> productData = productRepository.findById(id);
		if(productData.isPresent()) {
			Product _product = productData.get();
			_product.setProductType(product.getProductType());
			_product.setProductName(product.getProductName());
			_product.setCategory(product.getCategory());
			_product.setPrice(product.getPrice());
			_product.setDescription(product.getDescription());
			return new ResponseEntity<>(productRepository.save(_product), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/productId/{productId}")
	public ResponseEntity<?> deleteProductById(@PathVariable("productId") int id){
		try {
			productRepository.deleteById(id);
			return new ResponseEntity<>("Successfully deleted with id: " +id, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}	
	}
	
	@GetMapping("/productType/{productType}")
	public ResponseEntity<Product> getProductByType(@PathVariable("productType") String type){
		Optional<Product> productdata = productRepository.findByProductType(type);
		if(productdata.isPresent()) {
			return new ResponseEntity<>(productdata.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/category/{category}")
	public ResponseEntity<Product> getProductByCategory(@PathVariable("category") String category){
		Optional<Product> productdata = productRepository.getProductByCategory(category);
		if(productdata.isPresent()) {
			return new ResponseEntity<>(productdata.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping("/seq")
	public int nextId() {
		IdGenerator id = idRepository.findById("productId").get();
		id.setSeq(id.getSeq()+1);
		idRepository.save(id);
		return id.getSeq()-1;
	}
	
	
}
