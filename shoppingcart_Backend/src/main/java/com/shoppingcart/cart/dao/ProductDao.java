package com.shoppingcart.cart.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingcart.cart.entities.Product;

public interface ProductDao extends JpaRepository<Product, Long> {
	public List<Product> findByCategory(String category);
	public List<Product> findByCategoryContaining(String category);
}
