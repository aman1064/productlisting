package com.wingify.apiserver.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wingify.apiserver.entities.Product;
import com.wingify.apiserver.repositories.ProductRepository;
import com.wingify.apiserver.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Boolean deleteProduct(String productId) {
		if (productRepository.exists(productId)) {
			productRepository.delete(productId);
			return true;
		}
		throw new RuntimeException("Product Id not found");
	}

	@Override
	public Product getProduct(String productId) {
		Product product = productRepository.findOne(productId);
		if (product != null) {
			return product;
		}
		throw new RuntimeException("Product not found for given id");
	}

	@Override
	public List<Product> getProducts(String brand, String productType) {
		return null;
	}

	@Override
	public List<Product> getProducts(String brand, String productType, int page, int size) {
		if (StringUtils.isEmpty(brand) && StringUtils.isEmpty(productType)) {
			throw new RuntimeException("Both brand and productType can't be empty");
		}
		if (StringUtils.isEmpty(productType)) {
			return productRepository.findByProductDetailsBrand(brand, getPageRequest(page, size)).getContent();
		}
		if (StringUtils.isEmpty(brand)) {
			return productRepository.findByProductDetailsProductType(productType, getPageRequest(page, size))
					.getContent();
		}
		return productRepository
				.findByProductDetailsBrandAndProductDetailsProductType(brand, productType, getPageRequest(page, size))
				.getContent();
	}

	@Override
	public List<Product> getMatchingProducts(String text, int page, int size) {
		return productRepository.findByTitleRegex(text, getPageRequest(page, size)).getContent();
	}

	private PageRequest getPageRequest(int page, int size) {
		return new PageRequest(page, size);
	}

}
