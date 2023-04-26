package com.shoppingcart.cart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.cart.entities.Address;
import com.shoppingcart.cart.entities.CartItem;
import com.shoppingcart.cart.entities.Order;
import com.shoppingcart.cart.entities.OrderItem;
import com.shoppingcart.cart.entities.User;
import com.shoppingcart.cart.services.OrderService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/order/{userId}/getOrders")
	public List<OrderItem> getOrders(@PathVariable String userId) {
		return orderService.getOrders(Long.parseLong(userId));
	}

//	@PostMapping("/order/{userId}/createOrder")
//	public Order createOrder(@RequestBody CartItem cartItem, @PathVariable String userId) {
//		
//			Order order = new Order();
//			order.setUser(new User(Long.parseLong(userId)));
//			List<OrderItem> orderItems = new ArrayList<>();
//			OrderItem orderItem = new OrderItem();
//			orderItem.setOrder(order);
//			orderItem.setProduct(cartItem.getProduct());
//			orderItem.setQuantity(cartItem.getQuantity());
//			orderItem.setOrderStatus("Order Created");
//			orderItems.add(orderItem);
//		
//			order.setOrderItems(orderItems);
//		
//		
//		return orderService.createOrder(order);
//  }
	
	
	@PostMapping("/order/{userId}/createOrder")
	public List<Order> createOrder(@RequestBody List<CartItem> cartItemList, @PathVariable String userId) {
		List<Order> finalList=new ArrayList<>();
		Order order = new Order();
		order.setUser(new User(Long.parseLong(userId)));
		List<OrderItem> orderItems = new ArrayList<>();

		for(CartItem cartItem: cartItemList) {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setOrderStatus("Order Created");
			orderItems.add(orderItem);
		}
		order.setOrderItems(orderItems);
		orderService.createOrder(order);
		finalList.add(order);
		
		return finalList;
	}
	
	
	@GetMapping("/order/{orderId}/Address/{addressId}")
	public Address orderAddress(@PathVariable String orderId, @PathVariable String addressId) {
		return orderService.orderAddress(Long.parseLong(orderId), Long.parseLong(addressId));
	}

}
