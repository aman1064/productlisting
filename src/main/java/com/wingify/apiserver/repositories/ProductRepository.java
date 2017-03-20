package com.wingify.apiserver.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.wingify.apiserver.entities.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

	Page<Product> findByProductDetailsBrandAndProductDetailsProductType(String brand, String type, Pageable pagable);
	Page<Product> findByProductDetailsBrand(String brand, Pageable pagable);
	Page<Product> findByProductDetailsProductType(String productType, Pageable pagable);
	Page<Product> findByTitleRegex(String title,Pageable pageable);
}
