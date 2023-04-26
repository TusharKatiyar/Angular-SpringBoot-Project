package com.shoppingcart.cart.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingcart.cart.entities.OrderItem;

public interface OrderItemDao extends JpaRepository<OrderItem, Long> {
	public List<OrderItem> findAllByOrderId(Object orderId);
}
