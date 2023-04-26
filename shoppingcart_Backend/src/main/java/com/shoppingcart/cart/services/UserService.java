package com.shoppingcart.cart.services;

import org.springframework.http.ResponseEntity;

import com.shoppingcart.cart.entities.User;
import com.shoppingcart.cart.modal.UserLoginObj;

public interface UserService {
	public User getProfile(Long id);
	public ResponseEntity<Object> loginUser(UserLoginObj userLoginObj);
	public ResponseEntity<Object> signupUser(User user);
	public ResponseEntity<String> updateUser(User user);
	public ResponseEntity<String> logoutUser(Long id);
	public ResponseEntity<String> resetPassword(User user);
}
