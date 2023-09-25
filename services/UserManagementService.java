package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.User;

public interface UserManagementService {

	public static final String PASSWORD = "password";
	public static final String EMAIL = "email";
	String registerUser(User user);
	
	User[] getUsers();

	User getUserByEmail(String userEmail);
	String updateUser(int id, String oldOne, String newOne, String change);
	int findById(int id);

}
