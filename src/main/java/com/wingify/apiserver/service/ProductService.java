package com.wingify.apiserver.service;

import java.util.List;

import com.wingify.apiserver.entities.Product;

public interface ProductService {

	public Product saveProduct(Product product);
	public Boolean deleteProduct(String productId);
	public Product getProduct(String productId);
	public List<Product> getProducts(String brand,String productType);
	public List<Product> getProducts(String brand,String productType, int page, int size);
	public List<Product> getMatchingProducts(String text,int page, int size);
}
