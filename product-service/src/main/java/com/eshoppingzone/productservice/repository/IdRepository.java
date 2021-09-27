package com.eshoppingzone.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eshoppingzone.productservice.entity.IdGenerator;

public interface IdRepository extends MongoRepository<IdGenerator, String> {

}
