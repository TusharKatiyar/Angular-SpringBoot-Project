package com.shoppingcart.cart.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingcart.cart.entities.Product;
import com.shoppingcart.cart.modal.ImageObj;
import com.shoppingcart.cart.services.ProductService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;

	// Product Management

	// get all the products
	@GetMapping("/products")
	public List<Product> getProducts() {
		return this.productService.getProducts();
	}

	
	// add product
	@PostMapping("/products/addProduct")
	public Product addProduct(@RequestBody Product product) {
		System.out.println(product);
		System.out.println("??????????????");
		return this.productService.addProduct(product);
	}
	
	@PostMapping("/products/addImage/{productId}")
	public ResponseEntity<String> addImage(@RequestParam("image") MultipartFile image, @PathVariable(value="productId") Long productId) throws IOException {
		System.out.println(image);
		return this.productService.addImage(image,productId);
	}
	

	// update product
	@PostMapping("/products/update")
	public Product updateProduct(@RequestBody Product product) {
		return this.productService.updateProduct(product);
	}
	

	// get single product
	@GetMapping("/products/getById/{productId}")
	public Product getProduct(@PathVariable String productId) {
		return this.productService.getProduct(Long.parseLong(productId));
	}
	

	// get product by category
	@GetMapping("/products/{category}")
	public List<Product> getProductsByCategory(@PathVariable String category) {

		return this.productService.getProductsByCategory(category);
	}
	

	// get product by search string
	@GetMapping("/products/search/{searchString}")
	public List<Product> getProductsBySearch(@PathVariable String searchString) {

		return this.productService.getProductsBySearch(searchString);
	}
	
	
	// get filtered product by category
	@PostMapping("/products/{category}/getFilteredProducts")
	public List<Product> getProductsByCategoryWithFilter(@PathVariable String searchString, @RequestBody String[] filterNames) {

		return null;
	}
	

	// remove product
	@DeleteMapping("/products/getOne/{productId}")
	public ResponseEntity<String> removeProduct(@PathVariable String productId) {
		return this.productService.removeProduct(Long.parseLong(productId));
	}
}
