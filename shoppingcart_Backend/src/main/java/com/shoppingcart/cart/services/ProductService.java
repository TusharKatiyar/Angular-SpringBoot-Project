package com.shoppingcart.cart.services;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingcart.cart.entities.Product;
import com.shoppingcart.cart.modal.ImageObj;

public interface ProductService  {
	public List<Product> getProducts();

	public Product addProduct(Product product);
	
	public ResponseEntity<String> addImage(MultipartFile image, Long productId) throws IOException;

	public Product updateProduct(Product product);

	public Product getProduct(Long productId);

	public List<Product> getProductsByCategory(String category);

	public List<Product> getProductsBySearch(String searchString);

	public List<Product> getProductsByCategoryWithFilter(String category, String[] filters);

	public ResponseEntity<String> removeProduct(Long productId);
}
