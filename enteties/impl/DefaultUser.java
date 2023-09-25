package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.User;

public class DefaultUser implements User {

	String INCORRECT_PASSWORD = "Wrong password, try again";
	String CORRECT_PASSWORD = "";
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;

	public DefaultUser() {
	}

	public DefaultUser(int id, String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.id = id;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}
	public boolean comparePassword(String old) {
		return this.password.equals(old);
	}
	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public String changePassword(String old, String newP) {
		if(!old.equals(this.password)) return INCORRECT_PASSWORD;
		else {
			this.password = newP;
			return CORRECT_PASSWORD;
		}
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "\nFirst Name: " + firstName + "\nLast Name: " + lastName + " \nEmail Address: " + email;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setEmail(String newEmail) {
		this.email = newEmail;
	}

	@Override
	public int getId() {
		return id;
	}
}
