package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.configs.ApplicationContext;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.User;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.impl.DefaultUser;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.Menu;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.UserManagementService;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.impl.DefaultUserManagementService;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.utilities.Utility;

import java.util.*;
public class SignUpMenu implements Menu {
	Scanner scan = new Scanner(System.in);
	private UserManagementService userManagementService;
	private ApplicationContext context;

	{
		userManagementService = DefaultUserManagementService.getInstance();
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		String first;
		String last;
		String email;
		String password;
		printMenuHeader();
		Menu signUp = new SignUpMenu();
		System.out.print("Input your first name: ");
		first = scan.nextLine();
		while (first.isEmpty() || Utility.isMalformedString(first) || Utility.equalsReservedWord(first)) {
			System.out.println("\nERROR: First name must be valid");
			System.out.print("First Name: ");
			first = scan.nextLine();
		}
		System.out.print("\nInput your last name: ");
		last = scan.nextLine();

		while (last.isEmpty() || Utility.isMalformedString(last) || Utility.equalsReservedWord(last)) {
			System.out.println("\nERROR: Last name must be valid");
			System.out.print("Last Name: ");
			last = scan.nextLine();
		}
		System.out.print("\nInput your email: ");
		email = scan.nextLine();

		while (email.isEmpty() || Utility.isMalformedString(email) || Utility.equalsReservedWord(email)) {
			if(Utility.isMalformedString(email) && email.contains("@") && email.contains(".")) break;
			System.out.println("\nERROR: Email must be valid");
			System.out.print("Email: ");
			email = scan.nextLine();
		}
		System.out.print("\nInput your password: ");
		password = scan.nextLine();

		while (password.isEmpty() || Utility.equalsReservedWord(password)) {
			System.out.println("\nERROR: Password must not be empty");
			System.out.print("Password: ");
			password = scan.nextLine();
		}
		int len = userManagementService.getUsers() == null ? 0 : userManagementService.getUsers().length;
		User user = new DefaultUser(len, first, last, email, password);
		System.out.println("New User Created! Going back to main menu...");
		userManagementService.registerUser(user);
		context.setLoggedInUser(user);
		context.setPreviousMenu(signUp);
		context.getMainMenu().start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println("Registering New User:");
	}

}
