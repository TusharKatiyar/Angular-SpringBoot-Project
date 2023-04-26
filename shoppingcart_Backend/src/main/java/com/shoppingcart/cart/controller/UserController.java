package com.shoppingcart.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.cart.entities.User;
import com.shoppingcart.cart.modal.UserLoginObj;
import com.shoppingcart.cart.services.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {
	
		@Autowired
		private UserService userService;
	
		
		
		@GetMapping("/home")
		public String home() {
			return "this is home page";
		}
		
		
	
	// User Management
		@PostMapping("/login")
		public ResponseEntity<Object> getUser(@RequestBody UserLoginObj userLoginObj) {
			return this.userService.loginUser(userLoginObj);
		}
		
		
		

		@PostMapping("/signup")
		public ResponseEntity<Object> addUser(@RequestBody User user) {
				return this.userService.signupUser(user);
		}

		
		
		
		@PostMapping("/logout")
		public ResponseEntity<String> logoutUser(@RequestBody String userId) {
			try {
				this.userService.logoutUser(Long.parseLong(userId));
			} catch (Exception e) {
				// TODO: handle exception
				System.err.println(e.getMessage());
				return null;
			}
			return null;
		}

		
		
		
		@GetMapping("/getprofile/{userId}")
		public User getProfile(@PathVariable String userId) {
			try {
				return this.userService.getProfile(Long.parseLong(userId));
			} catch (Exception e) {
				// TODO: handle exception
			}
			return null;
		}
		

		@PostMapping("/updateProfile")
		public ResponseEntity<String> updateUser(@RequestBody User user) {
				return this.userService.updateUser(user);	
		}
		
		
		@PostMapping("/resetPassword")
		public ResponseEntity<String> resetPassword(@RequestBody User user){
			return this.userService.resetPassword(user);
		}
}
