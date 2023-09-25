package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties;

public interface User {
	
	String getFirstName();
	String getLastName();
	String getEmail();
	int getId();
	boolean comparePassword(String password);
	void setPassword(String newPassword);
	String changePassword(String oldPassword, String newPassword);
	void setEmail(String newEmail);
	
	
}
