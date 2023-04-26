package com.shoppingcart.cart.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_table")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
	@JsonIgnoreProperties("order")
	private List<OrderItem> orderItems;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "userId")
	private User user;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "addressId")
	private Address address;
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(long id, List<OrderItem> orderItems, User user, Address address) {
		super();
		this.id = id;
		this.orderItems = orderItems;
		this.user = user;
		this.address = address;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
