package com.shoppingcart.cart.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingcart.cart.entities.User;

public interface UserDao extends JpaRepository<User, Long> {
	
	public Optional<User> findByEmail(String email);
}
