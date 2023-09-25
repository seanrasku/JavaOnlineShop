package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.Cart;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.Product;

public class DefaultCart implements Cart {

	private Product[] cart;


	public DefaultCart() {
		cart = new Product[0];
	}
	@Override
	public boolean isEmpty() {
		// <write your code here>
		
		return cart.length == 0;
	}

	@Override
	public void addProduct(Product product) {
		if(product != null && product.getId() >= 1 && product.getId() <= 10) {
			int len = cart.length;
			Product[] tempCart = cart;
			cart = new Product[len + 1];
            System.arraycopy(tempCart, 0, cart, 0, len);
			cart[len] = product;
		}
	}

	@Override
	public Product[] getProducts() {
		return cart;
	}

	@Override
	public void clear() {
		cart = new Product[0];
	}


}
