package com.shoppingcart.cart.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.shoppingcart.cart.dao.UserDao;
import com.shoppingcart.cart.entities.User;
import com.shoppingcart.cart.modal.UserLoginObj;
import com.shoppingcart.cart.services.UserService;
import com.shoppingcart.cart.services.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	UserDao userDao;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	//tests
	@Test
	public void LoginIfCredentialIsCorrectTest() throws Exception {
		
		User user=new User();
		user.setId(1);
		user.setAddress(null);
		user.setEmail("a@gmail.com");
		user.setCartItems(null);
		user.setName("a");
		user.setPassword("a");
		when(userDao.findByEmail("a@gmail.com")).thenReturn(Optional.of(user));
		UserLoginObj userLoginObj= new UserLoginObj();
		userLoginObj.email=user.getEmail();
		userLoginObj.password=user.getPassword();
		
		ResponseEntity<Object> responseEntity=userService.loginUser(userLoginObj);
		User user1=(User) responseEntity.getBody();
		assertEquals("a", user1.getName());
	}
	
	@Test
	public void LoginIfCredentialIsNotCorrectTest() throws Exception {
		
		User user=new User();
		user.setId(1);
		user.setAddress(null);
		user.setEmail("a@gmail.com");
		user.setCartItems(null);
		user.setName("a");
		user.setPassword("a");
		when(userDao.findByEmail("a@gmail.com")).thenReturn(Optional.of(user));
		UserLoginObj userLoginObj= new UserLoginObj();
		userLoginObj.email=user.getEmail();
		userLoginObj.password="bad";
		
		ResponseEntity<Object> responseEntity=userService.loginUser(userLoginObj);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("result", "failure");
		assertEquals(jsonObject.toString(),responseEntity.getBody());
	}
	
}










