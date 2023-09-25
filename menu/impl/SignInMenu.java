package com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.impl;

import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.configs.ApplicationContext;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.enteties.User;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.menu.Menu;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.UserManagementService;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.services.impl.DefaultUserManagementService;
import com.itbulls.learnit.javacore.oop.exam.templates.onlineshop.utilities.Utility;

import java.util.Scanner;

public class SignInMenu implements Menu {

	private ApplicationContext context;
	private Scanner scan = new Scanner(System.in);

	private UserManagementService userManagementService;

	{
		context = ApplicationContext.getInstance();
		userManagementService = DefaultUserManagementService.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		String email;
		while((email = scan.nextLine()).isEmpty() || Utility.isMalformedString(email)) {
			if(Utility.isMalformedString(email) && email.contains("@") && email.contains(".")) break; //Not perfect for email, but would require much more in depth error checking and probable library imports to have ready in production
			System.out.println("Please enter a valid email");
			System.out.print(">>> ");
		}
		String password;
		System.out.println("Password");
		System.out.print(">>> ");
		while((password = scan.nextLine()).isEmpty()) { // Would obviously have further restrictions on password, but for project, this is good enough so user doesn't accidentally go past it
			System.out.println("Please enter a valid password");
			System.out.print(">>> ");
		}
		User u = userManagementService.getUserByEmail(email);
		if(u == null) {
			System.out.println("User does not exist, going back to main menu...");
		}
		else {
			boolean equal = u.comparePassword(password);
			if(equal) {
				context.setLoggedInUser(u);
				System.out.println("User found, login successful!");

			}
			else System.out.println("User found, password incorrect");
			System.out.println("Going back to main menu...");
		}
		context.getMainMenu().start();
	}


	@Override
	public void printMenuHeader() {
		System.out.println("Log In");
		System.out.println("Email:");
		System.out.print(">>> ");	}
}
