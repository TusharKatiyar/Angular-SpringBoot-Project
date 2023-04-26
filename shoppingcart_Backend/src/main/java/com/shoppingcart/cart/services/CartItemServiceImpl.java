package com.shoppingcart.cart.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.cart.Exception.ResourceNotFoundException;
import com.shoppingcart.cart.dao.CartItemDao;
import com.shoppingcart.cart.dao.ProductDao;
import com.shoppingcart.cart.dao.UserDao;
import com.shoppingcart.cart.entities.CartItem;
import com.shoppingcart.cart.entities.Product;
import com.shoppingcart.cart.entities.User;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemDao cartItemDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UserDao userDao;

	@Override
	public List<CartItem> getAllCartItems(Long userId) {
		return cartItemDao.findAllByUserId(userId);
	}

	@SuppressWarnings("deprecation")
	@Override
	public CartItem getCartItem(Long userId, Long cartItemId) {
		return cartItemDao.getById(cartItemId);
	}

	@Override
	public List<CartItem> addCartItem(Long userId, Long productId) {
		
			User user=userDao.findById(userId).orElseThrow(() -> {throw new ResourceNotFoundException("User does not exist");});
			Product product = productDao.findById(productId).orElseThrow(() -> {throw new ResourceNotFoundException("Product does not exist");});
			Optional<CartItem> cartItem= cartItemDao.findByUserIdAndProductId(userId, productId);
			
			if(cartItem.isPresent()) {
				cartItem.get().setQuantity(cartItem.get().getQuantity()+1);
				cartItemDao.save(cartItem.get());
				return getAllCartItems(cartItem.get().getUser().getId());
			}
			else {
				CartItem cartItem2=new CartItem();
				cartItem2.setProduct(product);
				cartItem2.setQuantity(1);
				cartItem2.setUser(user);
				cartItemDao.save(cartItem2);
				return getAllCartItems(cartItem2.getUser().getId());

			}
	}
	
	

	@Override
	public boolean removeCartItem(Long userId, Long productId) {
		cartItemDao.deleteByUserIdAndProductId(userId, productId);
		return true;
	}

	@Override
	public CartItem increment(Long userId, Long cartId) {
		Optional<CartItem> cartItem = cartItemDao.findById(cartId);
		cartItem.get().setQuantity(cartItem.get().getQuantity() + 1);
		return cartItemDao.save(cartItem.get());
	}
	
	@Override
	public CartItem decrement(Long userId, Long cartId) {
		Optional<CartItem> cartItem = cartItemDao.findById(cartId);
		cartItem.get().setQuantity(cartItem.get().getQuantity() - 1);
		return cartItemDao.save(cartItem.get());
	}

}
