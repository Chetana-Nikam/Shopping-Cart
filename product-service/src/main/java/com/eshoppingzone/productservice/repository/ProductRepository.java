package com.eshoppingzone.productservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eshoppingzone.productservice.entity.Product;

public interface ProductRepository extends MongoRepository<Product, Integer>{

	Optional<Product> findByProductName(String name);

	Optional<Product> findByProductType(String type);

	Optional<Product> getProductByCategory(String category);

}
