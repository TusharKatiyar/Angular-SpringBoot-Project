package com.shoppingcart.cart.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingcart.cart.entities.Address;

public interface AddressDao extends JpaRepository<Address, Long>{
	public List<Address> findAllByUserId(Long userId);
}
