package com.shoppingcart.cart.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingcart.cart.entities.User;
import com.shoppingcart.cart.modal.UserLoginObj;
import com.shoppingcart.cart.services.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@Test
	public void LoginUserTest() throws Exception {
		
		UserLoginObj userLoginObj= new UserLoginObj();
		userLoginObj.email="a@gmail.com";
		userLoginObj.password="a";
		
		User user=new User();
		user.setId(1);
		user.setAddress(null);
		user.setEmail("a@gmail.com");
		user.setCartItems(null);
		user.setName("a");
		user.setPassword("a");
		
		when(userService.loginUser(userLoginObj)).thenReturn(new ResponseEntity<Object>(user,HttpStatus.OK));
		ObjectMapper mapper=new ObjectMapper();
		String jsonString=mapper.writeValueAsString(userLoginObj);
		RequestBuilder requestBuilder=MockMvcRequestBuilders.post("/login").content(jsonString).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult resultMvc= mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andDo(print()).andReturn();
	}
}
