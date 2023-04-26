package com.shoppingcart.cart.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingcart.cart.entities.Order;

public interface OrderDao extends JpaRepository<Order, Long> {
	public List<Order> findAllByUserId(Object userId);
}
