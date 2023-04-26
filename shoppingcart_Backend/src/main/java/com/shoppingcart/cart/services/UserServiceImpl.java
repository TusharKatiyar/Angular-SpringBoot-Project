package com.shoppingcart.cart.services;

import java.util.Objects;
import java.util.Optional;

import org.json.JSONObject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.shoppingcart.cart.Exception.ResourceNotFoundException;
import com.shoppingcart.cart.dao.UserDao;
import com.shoppingcart.cart.entities.User;
import com.shoppingcart.cart.modal.UserLoginObj;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	
	
	@Override
	public ResponseEntity<Object> loginUser(UserLoginObj userLoginObj) {
		User loginUser=userDao.findByEmail(userLoginObj.email).orElseThrow(() -> {throw new ResourceNotFoundException("Email does not exist");});

		JSONObject jsonObj=new JSONObject();
		
		if(loginUser != null && Objects.equals(loginUser.getPassword(), userLoginObj.password)) {
			jsonObj.put("result", loginUser.toString());
			return new ResponseEntity<Object>(loginUser, HttpStatus.OK);

		}
		else jsonObj.put("result", "failure");
		return new ResponseEntity<Object>(jsonObj.toString(), HttpStatus.UNAUTHORIZED);
	}
	
	
	
	

	@Override
	public ResponseEntity<Object> signupUser(User user) {
		Optional<User> signupUser=userDao.findByEmail(user.getEmail());
		
		JSONObject jsonObj=new JSONObject();
		if(!signupUser.isPresent()) {
			User user1=userDao.save(user);
			jsonObj.put("userId", user1.getId());
			return new ResponseEntity<Object>(user1,HttpStatus.OK);
		}
		else {
			jsonObj.put("userId", "Failure");
			return new ResponseEntity<Object>(jsonObj.toString(),HttpStatus.UNAUTHORIZED);
		}
	}

	
	
	@Override
	public ResponseEntity<String> updateUser(User user) {
		User updateUser=userDao.findByEmail(user.getEmail()).orElseThrow(() -> {throw new ResourceNotFoundException("Email does not exist");});

		JSONObject jsonObj=new JSONObject();

		if(Objects.equals(updateUser.getPassword(), user.getPassword())) {
			updateUser.setEmail(user.getEmail());
			updateUser.setName(user.getName());
			updateUser.setPhone(user.getPhone());
			updateUser = userDao.save(user);
			jsonObj.put("result: ","Success");
			return new ResponseEntity<String>(jsonObj.toString(),HttpStatus.OK);
		}
		else {
			jsonObj.put("result: ", "Failure");
			return new ResponseEntity<String>(jsonObj.toString(),HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	

	@Override
	public User getProfile(Long id) {
		
		@SuppressWarnings("deprecation")
		User getProfile=userDao.getById(id);
		
  		return getProfile;
	}

	
	@Override
	public ResponseEntity<String> logoutUser(Long id) {

		JSONObject jsonObj=new JSONObject();
		
		return null;
	
	}
	
	@Override
	public ResponseEntity<String> resetPassword(User user){
		JSONObject jsonObj=new JSONObject();
		
		User resetUser=userDao.findByEmail(user.getEmail()).orElseThrow(() -> {throw new ResourceNotFoundException("Email does not exist");});

			resetUser.setPassword(user.getPassword());
			user.setId(resetUser.getId());
			user.setName(resetUser.getName());
			user.setAddress(resetUser.getAddress());
			user.setPhone(resetUser.getPhone());
			resetUser=userDao.save(user);
			jsonObj.put("result", "Success");
			return new ResponseEntity<String>(jsonObj.toString(),HttpStatus.OK);
			
	}

}
