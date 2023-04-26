package com.shoppingcart.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.cart.entities.CartItem;
import com.shoppingcart.cart.entities.Product;
import com.shoppingcart.cart.entities.Quantity;
import com.shoppingcart.cart.entities.User;
import com.shoppingcart.cart.services.CartItemService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CartController {

	@Autowired
	private CartItemService cartItemService;
	
	
	//get user cart
	@GetMapping("/cart/{userId}/getCart")
	public List<CartItem> getAllCartItems(@PathVariable String userId) {
		return cartItemService.getAllCartItems(Long.parseLong(userId));
	}

	//get one cart item
	@GetMapping("/cart/{userId}/getCartItem/{cartItemId}")
	public CartItem getCartItem(@PathVariable String userId, @PathVariable String cartItemId) {
		return cartItemService.getCartItem(Long.parseLong(userId), Long.parseLong(cartItemId));
	}

	
	//add to cart
	@GetMapping("/cart/{userId}/add/{productId}")
	public List<CartItem> addCartItem(@PathVariable String userId, @PathVariable String productId) {
		return cartItemService.addCartItem(Long.parseLong(userId),Long.parseLong(productId));
	}
	
	
	//delete from cart
	@DeleteMapping("/cart/{userId}/remove/{productId}")
	public boolean removeCartItem(@PathVariable String userId, @PathVariable String productId) {
		return cartItemService.removeCartItem(Long.parseLong(userId), Long.parseLong(productId));
	}

	//increment 
	@GetMapping("cart/{userId}/increment/{productId}")
	public CartItem increment(@PathVariable String userId,@PathVariable String productId) {
		return cartItemService.increment(Long.parseLong(userId),Long.parseLong(productId));
	}
	
	//decrement
	@GetMapping("cart/{userId}/decrement/{productId}")
	public CartItem decrement(@PathVariable String userId,@PathVariable String productId) {
		return cartItemService.decrement(Long.parseLong(userId),Long.parseLong(productId));
	}
}
