package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.User;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.UserManagementService;

import java.util.Objects;

public class DefaultUserManagementService implements UserManagementService {


	private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user. Please, use another email";
	private static final String EMPTY_EMAIL_ERROR_MESSAGE = "You have to input email to register. Please, try one more time";
	private static final String NO_ERROR_MESSAGE = "";
	public static final String PASSWORD = "password";
	public static final String EMAIL = "email";
	final String ERROR_SET = "Could not set email/password, please try again";

	private static final int DEFAULT_USERS_CAPACITY = 10;

	private static DefaultUserManagementService instance;

	private User[] users;
	private int lastUserIndex;

	private DefaultUserManagementService() {
		users = new User[DEFAULT_USERS_CAPACITY];
		lastUserIndex = 0;
	}

	@Override
	public String registerUser(User user) {
		if(lastUserIndex == 0) {
			users[lastUserIndex] = user;
			lastUserIndex++;
			return NO_ERROR_MESSAGE;
		}
		else {
			if (user == null) return null;
			else if (user.getEmail() == null) return EMPTY_EMAIL_ERROR_MESSAGE;
			else if (getUserByEmail(user.getEmail()) != null) return NOT_UNIQUE_EMAIL_ERROR_MESSAGE;
			else {
				users[lastUserIndex] = user;
				lastUserIndex++;
				return NO_ERROR_MESSAGE;
			}
		}
	}
	public String updateUser(int id, String oldOne, String newOne, String change) {
		if(oldOne == null || newOne == null || oldOne.isEmpty() || newOne.isEmpty()) return ERROR_SET;
		if(!change.equals(PASSWORD) && !change.equals(EMAIL)) return ERROR_SET;
		int ind = findById(id);
		if(ind == -1) return ERROR_SET;
		else {
            switch (change) {
                case PASSWORD -> {
                    users[ind].changePassword(oldOne, newOne);
                }
                case EMAIL -> {
                    users[ind].setEmail(newOne);
                }
            }
			return NO_ERROR_MESSAGE;
		}
	}
	public int findById(int id) {
		int i = 0;
		for(User u : users) {
			if(u == null) break;
			if(u.getId() == id) {
				return i;
			}
			i++;
		}
		return -1;
	}

	public static UserManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultUserManagementService();
		}
		return instance;
	}


	@Override
	public User[] getUsers() {
		return users;
	}

	@Override
	public User getUserByEmail(String userEmail) throws IllegalArgumentException {
		if(users.length == 0) {
			throw new IllegalArgumentException("Should never be searching while users is length 0");
		}
		for(User u : users) {
			if(u == null) break;
			if(u.getEmail().equals(userEmail)) {
				return u;
			}
		}
		return null;
	}

	void clearServiceState() {
		users = new User[DEFAULT_USERS_CAPACITY];
		lastUserIndex = 0;
	}
}
