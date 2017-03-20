package com.wingify.apiserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wingify.apiserver.entities.GenericResponse;
import com.wingify.apiserver.entities.Product;
import com.wingify.apiserver.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;

	@RequestMapping(value = "/api/product", method = RequestMethod.PUT)
	public GenericResponse<Product> createProduct(@RequestBody Product product) {
		if (StringUtils.isEmpty(product.getProductid())) {
			return new GenericResponse<Product>(productService.saveProduct(product));
		}
		throw new RuntimeException("Looks like you are trying to update product details. Use POST api for it");

	}

	@RequestMapping(value = "/api/product", method = RequestMethod.POST)
	public GenericResponse<Product> updateProduct(@RequestBody Product product) {
		if (StringUtils.isEmpty(product.getProductid())) {
			throw new RuntimeException("For updating the product details, productid is mandatory");
		}
		return new GenericResponse<Product>(productService.saveProduct(product));
	}

	@RequestMapping(value = "/api/product", method = RequestMethod.GET)
	public GenericResponse<Product> getProduct(@RequestParam(value = "productid") String productId) {
		return new GenericResponse<>(productService.getProduct(productId));
	}

	@RequestMapping(value = "/api/product", method = RequestMethod.DELETE)
	public GenericResponse<Boolean> deleteProduct(@RequestParam(value = "productid") String productId) {
		return new GenericResponse<>(productService.deleteProduct(productId));
	}

	@RequestMapping(value = "/api/products", method = RequestMethod.GET)
	public GenericResponse<List<Product>> getProductsByBrand(
			@RequestParam(value = "brand", required = false, defaultValue = "") String brand,
			@RequestParam(value = "type", required = false, defaultValue = "") String productType,
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
		return new GenericResponse<>(productService.getProducts(brand, productType, page, size));
	}

	@RequestMapping(value = "/api/product/search", method = RequestMethod.GET)
	public GenericResponse<List<Product>> searchProducts(
			@RequestParam(value = "search") String search,
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
		return new GenericResponse<>(productService.getMatchingProducts(search, page, size));
	}

}
