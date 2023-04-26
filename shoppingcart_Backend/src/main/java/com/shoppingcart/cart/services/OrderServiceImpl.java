package com.shoppingcart.cart.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.cart.dao.AddressDao;
import com.shoppingcart.cart.dao.OrderDao;
import com.shoppingcart.cart.dao.OrderItemDao;
import com.shoppingcart.cart.entities.Address;
import com.shoppingcart.cart.entities.Order;
import com.shoppingcart.cart.entities.OrderItem;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrderItemDao orderItemDao; 
	
	@Autowired AddressDao addressDao;

	@Override
	public List<OrderItem> getOrders(Long userId) {
		
		List<Order> orderList=orderDao.findAllByUserId(userId);
		List<OrderItem> orderItems=new ArrayList<>();
		for(Order order:orderList) {
			orderItems.addAll(orderItemDao.findAllByOrderId(order.getId()));	
		}
		return orderItems;
		
//		Order order=orderDao.findByUserId(userId);
//		List<OrderItem> orderItems=new ArrayList<>();
//		orderItems=order.getOrderItems();
//		return orderItems;
	}

	@Override
	public Order createOrder(Order order) {
		return orderDao.save(order);
	}
	
	@Override
	public Address orderAddress(Long orderId, Long addressId) {
		Optional<Order> order=orderDao.findById(orderId);
		Optional<Address> address2=addressDao.findById(addressId);
		order.get().setAddress(address2.get());
		orderDao.save(order.get());
		return address2.get();
	}

}
