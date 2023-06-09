package com.shoppingcart.cart.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "orderId")
	private Order order;
	
	@OneToOne
	@JoinColumn(name = "productId")
	private Product product;
	
	private long quantity;
	
	private String orderStatus;

	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderItem(long id, Order order, Product product, long quantity, String orderStatus) {
		super();
		this.id = id;
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.orderStatus = orderStatus;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
}
