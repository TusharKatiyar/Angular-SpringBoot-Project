package com.shoppingcart.cart.services;

import java.io.IOException;
import java.util.*;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shoppingcart.cart.dao.ProductDao;
import com.shoppingcart.cart.entities.Product;
import com.shoppingcart.cart.entities.User;
import com.shoppingcart.cart.modal.ImageObj;


@Service
public class ProductServiceImpl implements ProductService{ 
	
	@Autowired
	private ProductDao productDao;



	@Override
	public List<Product> getProducts() {
		return productDao.findAll();
	}


	@Override
	public Product addProduct(Product product) {	
		return productDao.save(product);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public ResponseEntity<String> addImage(MultipartFile image, Long productId) throws IOException{
		Product product=productDao.getById(productId);
		product.setImage(image.getBytes());
		productDao.save(product);
		JSONObject jsonObj=new JSONObject();
		jsonObj.put("Result", "Success");
		
		return new ResponseEntity<String>(jsonObj.toString(),HttpStatus.OK);
	}

	@Override
	public Product updateProduct(Product product) {
		return productDao.save(product);
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	public Product getProduct(Long productId) {
		return productDao.getById(productId);
	}
	

	@Override
	public List<Product> getProductsByCategory(String category) {
		return productDao.findByCategory(category);
	}

	@Override
	public List<Product> getProductsBySearch(String searchString) {
		return productDao.findByCategoryContaining(searchString);
	}

	@Override
	public List<Product> getProductsByCategoryWithFilter(String category, String[] filters) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	public ResponseEntity<String> removeProduct(Long productId) {
		Product product = productDao.getOne(productId);
		JSONObject jsonObj=new JSONObject();
		
		if(product!=null) {
			productDao.delete(product);
			jsonObj.put("result", "Success");
			return new ResponseEntity<String>(jsonObj.toString(),HttpStatus.OK);
		}
		else {
			jsonObj.put("result", "Failure");
			return new ResponseEntity<String>(jsonObj.toString(),HttpStatus.UNAUTHORIZED);
		}
	}

}
