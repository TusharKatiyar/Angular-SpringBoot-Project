package com.shoppingcart.cart.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.cart.Exception.ResourceNotFoundException;
import com.shoppingcart.cart.dao.AddressDao;
import com.shoppingcart.cart.dao.UserDao;
import com.shoppingcart.cart.entities.Address;
import com.shoppingcart.cart.entities.User;


@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private UserDao userDao;
	
	
	@Override
	public List<Address> getAddresses(Long userId) {
		return addressDao.findAllByUserId(userId);
	}
	
	
	@Override
	public List<Address> addAddress(Address address, Long userId) {
		
		User user = userDao.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
		address.setUser(user);
		addressDao.save(address);
		user.getAddress().add(address);
		userDao.save(user);
		return user.getAddress();
	}
	

	@Override
	public Address updateAddress(Address address, Long addressId) {
		Optional<Address> address2=addressDao.findById(addressId);
		Address address3 = address2.get();
		address3.setCity(address.getCity());
		address3.setPincode(address.getPincode());
		address3.setState(address.getState());
		address3.setStreet(address.getStreet());
		
		return addressDao.save(address3);
	}
	

	@Override
	public boolean removeAddress(Long addressId, Long userId) {
		
		Optional<Address> address = addressDao.findById(addressId);
        if (address.isPresent()) {
        	
        	User user=userDao.findById(userId)
        			.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        	
        	user.getAddress().remove(address.get());
        	userDao.save(user);
            addressDao.delete(address.get());
            return true;
        }
        return false;
	}
}
