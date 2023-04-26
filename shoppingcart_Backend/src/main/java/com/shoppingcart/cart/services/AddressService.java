package com.shoppingcart.cart.services;

import java.util.List;

import com.shoppingcart.cart.entities.Address;


public interface AddressService {
	public List<Address> getAddresses(Long userId);
	public List<Address> addAddress(Address address, Long userId);
	public Address  updateAddress(Address address, Long addressId);
	public boolean removeAddress(Long addressId, Long userId);
}
