package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.Order;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.Product;

import java.util.Arrays;

public class DefaultOrder implements Order {

	private static final int AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER = 16;
	
	private String creditCardNumber;
	private Product[] products;
	private int customerId;


	@Override
	public boolean isCreditCardNumberValid(String creditCardNumber) {
		if(creditCardNumber.length() != AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER) return false;
		try {
			for(int i = 0; i < AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER; i++) {
				String s = "";
				s += creditCardNumber.charAt(i);
				Integer.parseInt(s);
			}
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	@Override
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	@Override
	public void setProducts(Product[] products) {
		this.products = products;
	}

	@Override
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	@Override
	public int getCustomerId() {
		return this.customerId;
	}
	
	@Override
	public String toString() {
		return "\nCustomer ID: " + customerId + "\nProducts: " + Arrays.toString(products);
	}
}
