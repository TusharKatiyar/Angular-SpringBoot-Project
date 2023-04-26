package com.shoppingcart.cart.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingcart.cart.entities.CartItem;

import jakarta.transaction.Transactional;

public interface CartItemDao extends JpaRepository<CartItem, Long> {
	public List<CartItem> findAllByUserId(Object userId);
	@Transactional
	void deleteByUserIdAndProductId(Object userId, Object productId);
	public Optional<CartItem> findByUserIdAndProductId(Object userId, Object productId);
}
