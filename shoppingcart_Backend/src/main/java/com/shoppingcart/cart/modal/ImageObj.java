package com.shoppingcart.cart.modal;

import org.springframework.web.multipart.MultipartFile;

public class ImageObj {
	public String name;
	public String description;
	public String category;
	public String[] subcategory;
	public byte[] image;
	public double price;
	
	public MultipartFile multipartFile;

}
