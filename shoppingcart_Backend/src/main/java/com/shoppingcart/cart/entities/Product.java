package com.shoppingcart.cart.entities;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	
	@Column(name = "image", unique = false, nullable = false, length = 100000)
	private byte[] image;
	private String description;
	private double price;
	private String category;
	private String[] subcategory;
	
	public Product() {
		super();
	}
	
	public Product(long id) {
		super();
		this.id = id;
	}

	public Product(long id, String name, byte[] image, String description, double price, String category,
			String[] subcategory) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.description = description;
		this.price = price;
		this.category = category;
		this.subcategory = subcategory;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String[] getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String[] subcategory) {
		this.subcategory = subcategory;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", image=" + image + ", description=" + description + ", price="
				+ price + ", category=" + category + ", subcategory=" + Arrays.toString(subcategory) + "]";
	}
	
}
