package com.shoppingcart.cart.services;

import java.util.List;

import com.shoppingcart.cart.entities.CartItem;

public interface CartItemService {
	public List<CartItem> getAllCartItems(Long userId);

	public CartItem getCartItem(Long userId, Long cartItemId);

	public List<CartItem> addCartItem(Long userId, Long productId);

	public CartItem increment(Long userId, Long productId);
	
	public CartItem decrement(Long userId, Long productId);
	
	public boolean removeCartItem(Long userId, Long productId);
}
