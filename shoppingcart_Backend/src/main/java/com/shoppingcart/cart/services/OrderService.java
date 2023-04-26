package com.shoppingcart.cart.services;

import java.util.List;

import com.shoppingcart.cart.entities.Address;
import com.shoppingcart.cart.entities.Order;
import com.shoppingcart.cart.entities.OrderItem;

public interface OrderService {
	
	public List<OrderItem> getOrders(Long userId);
	public Order createOrder(Order order);
	public Address orderAddress(Long orderId, Long addressId);
}
